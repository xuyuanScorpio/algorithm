package com.algorithm.heap.solution.mergeOrderSmallFiles;

import com.algorithm.heap.solution.mergeOrderSmallFiles.domain.ByteHeadInfo;
import com.algorithm.heap.solution.mergeOrderSmallFiles.domain.FileMergeInfo;
import com.algorithm.heap.solution.mergeOrderSmallFiles.domain.OutFile;

import java.io.File;
import java.util.PriorityQueue;

public class MergeProcess {

    public static final MergeProcess INSTANCE = new MergeProcess();

    public FileMergeInfo[] getMergeInfos(String path){

        FileMergeInfo[] fileMergeInfos = null;

        File file = new File(path);

        if(file.exists()){
            File[] files = file.listFiles();
            fileMergeInfos = new FileMergeInfo[files.length];

            for(int i = 0; i < files.length; i++){
                fileMergeInfos[i] = FileMergeProcess.INSTANCE.openFile(files[i]);
            }
        }
        return fileMergeInfos;
    }

    public void reader(FileMergeInfo[] mergeInfos, String outPath){
        for(int i = 0; i < mergeInfos.length; i++){
            FileMergeProcess.INSTANCE.readFile(mergeInfos[i]);
        }

        // 开始合并操作
        // 构建小顶堆
        PriorityQueue<ByteHeadInfo> smallHeap = new PriorityQueue<ByteHeadInfo>(mergeInfos.length, (o1, o2) -> {
            if(o1.getValue() > o2.getValue()){
                return 1;
            }else if(o1.getValue() < o2.getValue()){
                return -1;
            }
            return 0;
        });

        for(int i = 0; i < mergeInfos.length; i++){
            if(mergeInfos[i].getBufferReadIndex() <= mergeInfos[i].getFileReadIndex()){
                smallHeap.offer(new ByteHeadInfo(mergeInfos[i].getBuffer()[mergeInfos[i].getBufferReadIndex()], i));
                mergeInfos[i].setBufferReadIndex(mergeInfos[i].getBufferReadIndex() + 1);
            }
        }

        OutFile outFile = FileOutProcess.INSTANCE.openFile(outPath);
        ByteHeadInfo currentByte;

        // 如果当前文件没有结束，则继续遍历
        while(!checkFinish(mergeInfos) || !smallHeap.isEmpty()){
            currentByte = smallHeap.poll();

            // 如果当前缓冲区未满，则写入缓冲区
            if(outFile.getOutIndex() < outFile.getMaxIndex()){
                outFile.getBuffer()[outFile.getOutIndex()] = currentByte.getValue();
                outFile.setOutIndex(outFile.getOutIndex() + 1);
            }

            // 当缓冲区满了，写入文件
            if(outFile.getOutIndex() == outFile.getMaxIndex()){
                FileOutProcess.INSTANCE.fileWrite(outFile);
            }

            // 将当前文件的下一个数据加入到当前小顶堆中
            this.readFileNextByte(mergeInfos[currentByte.getIndex()], currentByte.getIndex(), smallHeap);

        }





    }

    private void readFileNextByte(FileMergeInfo mergeInfo, int index, PriorityQueue<ByteHeadInfo> smallHeap){
        // 如果当前的缓冲区还未读完，从缓冲区读取
        if(mergeInfo.getBufferReadIndex() < mergeInfo.getFileReadIndex()){
            smallHeap.offer(new ByteHeadInfo(mergeInfo.getBuffer()[mergeInfo.getBufferReadIndex()], index));
            mergeInfo.setBufferReadIndex(mergeInfo.getBufferReadIndex() + 1);
        }

        // 如果已经读取完成，则写入文件中
        if(mergeInfo.getBufferReadIndex() == mergeInfo.getFileReadIndex()){
            FileMergeProcess.INSTANCE.readFile(mergeInfo);
        }
    }

    private boolean checkFinish(FileMergeInfo[] infos){
        for(int i = 0; i < infos.length; i++){
            if(!infos[i].isFinish()){
                return false;
            }
        }
        return true;
    }

}
