package com.algorithm.heap.solution.statisticLogsKeys;

import com.algorithm.heap.solution.statisticLogsKeys.domain.PartitionInfo;
import com.algorithm.utils.IOUtils;

import java.io.*;

public class PartitionFile {

    public static final PartitionFile INSTANCE = new PartitionFile();

    private static final int MAX_PARTITION_SIZE = 4;

    private static final String PARTITION_DIR_NAME = "partition";

    private static final String SUFFIXE_NAME = ".buffer";

    public PartitionInfo[] getPartitions(String basePath){

        PartitionInfo[] partitionInfos = new PartitionInfo[MAX_PARTITION_SIZE];

        File baseFile = new File(basePath + File.separator + PARTITION_DIR_NAME);

        if(baseFile.exists()){
            File[] rsp = baseFile.listFiles();

            for(File file : rsp){
                file.delete();
            }

            System.out.println("删除结果：" + rsp);
        }
        baseFile.mkdir();

        for(int i = 0; i < MAX_PARTITION_SIZE; i++){
            partitionInfos[i] = new PartitionInfo();
            partitionInfos[i].setIndex(i);
            String path = baseFile.getPath() + File.separator + i + SUFFIXE_NAME;
            partitionInfos[i].setPath(path);
            // 生成输出文件
            try {
                partitionInfos[i].setOutWrite(new FileWriter(path));
                partitionInfos[i].setBufferedWriter(new BufferedWriter(partitionInfos[i].getOutWrite()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return partitionInfos;
    }

    public void writeData(PartitionInfo info, String data){

        try {
            info.getBufferedWriter().write(data);
            info.getBufferedWriter().newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeOutput(PartitionInfo[] partitionInfos){
        for(int i = 0; i < partitionInfos.length; i++){
            IOUtils.closeStream(partitionInfos[i].getBufferedReader());
            IOUtils.closeStream(partitionInfos[i].getOutWrite());
        }
    }

    public void openReader(PartitionInfo partitionInfo){
        try {
            partitionInfo.setFileReader(new FileReader(partitionInfo.getPath()));
            partitionInfo.setBufferedReader(new BufferedReader(partitionInfo.getBufferedReader()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeReader(PartitionInfo partitionInfo){
        IOUtils.closeStream(partitionInfo.getFileReader());
        IOUtils.closeStream(partitionInfo.getBufferedReader());
    }

}
