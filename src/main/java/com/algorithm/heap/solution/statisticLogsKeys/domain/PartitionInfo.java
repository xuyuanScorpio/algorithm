package com.algorithm.heap.solution.statisticLogsKeys.domain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class PartitionInfo {

    // 文件切分后的文件编号
    private int index;

    // 文件路径
    private String path;

    // 文件流输出对象
    private FileWriter outWrite;

    private BufferedWriter bufferedWriter;

    // 文件读取
    private FileReader fileReader;

    // 缓冲读取
    private BufferedReader bufferedReader;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FileWriter getOutWrite() {
        return outWrite;
    }

    public void setOutWrite(FileWriter outWrite) {
        this.outWrite = outWrite;
    }

    public FileReader getFileReader() {
        return fileReader;
    }

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }
}
