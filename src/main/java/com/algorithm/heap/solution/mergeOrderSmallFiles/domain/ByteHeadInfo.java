package com.algorithm.heap.solution.mergeOrderSmallFiles.domain;

public class ByteHeadInfo {

    private byte value;

    private int index;

    public ByteHeadInfo(byte value, int index) {
        this.value = value;
        this.index = index;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ByteHeadInfo{");
        sb.append("value=").append(value);
        sb.append(", index=").append(index);
        sb.append("}");
        return sb.toString();
    }
}
