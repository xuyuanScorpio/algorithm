package com.algorithm.heap.solution.statisticLogsKeys.domain;

public class Key {

    private String key;

    private int appearNum;

    public Key(String key, int appearNum) {
        this.key = key;
        this.appearNum = appearNum;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getAppearNum() {
        return appearNum;
    }

    public void setAppearNum(int appearNum) {
        this.appearNum = appearNum;
    }

}
