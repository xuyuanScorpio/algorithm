package com.algorithm.heap.solution.mergeOrderSmallFiles.domain;

import java.io.FileInputStream;

public class FileMergeInfo {

    // 数据读取
    private FileInputStream inputStream;

    // 读取数据信息
    private byte[] buffer;

    // 文件读取路径
    private String readPath;

    // 缓冲区读取索引号
    private int bufferReadIndex;

    // 文件中读取的缓存区大小
    private int fileReadIndex;

    // 是否结束
    private boolean finish;

    public FileInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(FileInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    public String getReadPath() {
        return readPath;
    }

    public void setReadPath(String readPath) {
        this.readPath = readPath;
    }

    public int getBufferReadIndex() {
        return bufferReadIndex;
    }

    public void setBufferReadIndex(int bufferReadIndex) {
        this.bufferReadIndex = bufferReadIndex;
    }

    public int getFileReadIndex() {
        return fileReadIndex;
    }

    public void setFileReadIndex(int fileReadIndex) {
        this.fileReadIndex = fileReadIndex;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
