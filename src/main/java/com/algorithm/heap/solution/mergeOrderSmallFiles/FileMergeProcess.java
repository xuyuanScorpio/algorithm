package com.algorithm.heap.solution.mergeOrderSmallFiles;

import com.algorithm.heap.solution.mergeOrderSmallFiles.domain.FileMergeInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileMergeProcess {

    public static final FileMergeProcess INSTANCE = new FileMergeProcess();

    private static final int MAX_BUFFER_SIZE = 10;

    public FileMergeInfo openFile(File file){

        FileMergeInfo fileMergeInfo = new FileMergeInfo();

        if(file.exists()){
            try {
                fileMergeInfo.setInputStream(new FileInputStream(file));
                fileMergeInfo.setReadPath(file.getPath());
                fileMergeInfo.setBuffer(new byte[MAX_BUFFER_SIZE]);
                fileMergeInfo.setFileReadIndex(0);
                fileMergeInfo.setBufferReadIndex(0);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return  fileMergeInfo;
    }

    public void readFile(FileMergeInfo info){
        if(!info.isFinish()){
            try {
                int readLength = info.getInputStream().read(info.getBuffer());
                info.setFileReadIndex(readLength);
                info.setBufferReadIndex(0);
                if(readLength == -1){
                    info.setFinish(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(FileMergeInfo[] infos){
        for(int i = 0; i < infos.length; i++){
            if(null != infos[i]){
                try {
                    infos[i].getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
