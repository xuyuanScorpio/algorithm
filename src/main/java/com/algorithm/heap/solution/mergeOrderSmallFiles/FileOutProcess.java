package com.algorithm.heap.solution.mergeOrderSmallFiles;

import com.algorithm.heap.solution.mergeOrderSmallFiles.domain.OutFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutProcess {

    public static final FileOutProcess INSTANCE = new FileOutProcess();

    private static final int MAX_BUFFER = 10;

    public OutFile openFile(String path){

        OutFile outFile = new OutFile(MAX_BUFFER);

        outFile.setOutPath(path);

        try {
            outFile.setOutputStream(new FileOutputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return outFile;
    }

    public void fileWrite(OutFile outFile){

        FileOutputStream outputStream = outFile.getOutputStream();

        try {
            outputStream.write(outFile.getBuffer(), 0, outFile.getOutIndex());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        outFile.setOutIndex(0);

    }

    public void close(OutFile outFile){

        if(null != outFile){
            try {
                outFile.getOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
