package com.algorithm.heap.solution.statisticLogsKeys;

import com.algorithm.Hash.consistentHashing.HashCode;
import com.algorithm.heap.solution.statisticLogsKeys.domain.Key;
import com.algorithm.heap.solution.statisticLogsKeys.domain.PartitionInfo;
import com.algorithm.utils.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BigFileTopN {

    private static final String DIVIDE_FLAG = " ";

    public Key[] topN(String path, int n){
        File file = new File(path);

        // 将当前一个文件切分到多个文件中，按关键字的hash进行分片操作，hash 算法使用 FNV1_32_HASH计算，分拆到多个文件中
        PartitionInfo[] partitionInfos = PartitionFile.INSTANCE.getPartitions(file.getParent());
        this.fileToPatition(path, partitionInfos);

        // 关闭所有分片文件，已写入磁盘
        PartitionFile.INSTANCE.closeOutput(partitionInfos);

        // 针对每个文件进行求TopN
        List<Key[]> keyList = this.countTopN(partitionInfos, n);

        // 针对获取的TopN在获取总的TopN即为最终的TopN
        Key[] keys = CountTopN.INSTANCE.getFinalTopN(keyList, n);
        return keys;
    }


    private List<Key[]> countTopN(PartitionInfo[] partitionInfos, int n){
        List<Key[]> list = new ArrayList<>();

        for(int i = 0; i < partitionInfos.length; i++){
            PartitionFile.INSTANCE.openReader(partitionInfos[i]);

            try {
                String line = null;
                while((line = partitionInfos[i].getBufferedReader().readLine()) != null){
                    CountTopN.INSTANCE.putData(line);
                }
                list.add(CountTopN.INSTANCE.getTopN(n));

                CountTopN.INSTANCE.dataClear();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                PartitionFile.INSTANCE.closeReader(partitionInfos[i]);
            }
        }

        return list;
    }

    private void fileToPatition(String path, PartitionInfo[] partitionInfos){

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        String line = null;

        try {
            fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null){

                // 将行数据输入分片到文件信息中
                this.lineToPartition(line, partitionInfos);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(bufferedReader);
            IOUtils.closeStream(fileReader);
        }


    }


    private void lineToPartition(String line, PartitionInfo[] partitionInfos){

        int index = 0;
        int findIndex;
        line = line.trim();

        while(index < line.length()){
            if((findIndex = line.indexOf(DIVIDE_FLAG, index)) != -1){
                String key = line.substring(index, findIndex);
                // 按关键字进行分片操作
                this.keyToPartition(key, partitionInfos);
                index += line.length();
            }
        }
    }


    private void keyToPartition(String key, PartitionInfo[] partitionInfos){
        int partLength = partitionInfos.length;
        // 计算hash值
        int hashCode = HashCode.getHash(key);
        // 计算得到分片
        int modeValue = hashCode % partLength;
        // 将数据输出到分片文件中
        PartitionFile.INSTANCE.writeData(partitionInfos[modeValue], key);
    }


}
