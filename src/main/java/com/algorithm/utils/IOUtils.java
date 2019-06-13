package com.algorithm.utils;

import java.io.Closeable;
import java.io.IOException;

public class IOUtils {

    public static void closeStream(Closeable stream){

        if(null != stream){
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
