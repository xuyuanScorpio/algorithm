package com.algorithm.heap.solution.midNum;

import java.util.PriorityQueue;

/**
 * 获取中位数
 * 思路：使用两个堆  大顶堆（前半部分数据） 小顶堆（后半部分数据）
 *      1、动态数据插入时间复杂度O(logN)
 *      2、获取中位数O(1)
 * 回顾：堆化操作（自下往上、自上往下）
 */
public class MidNum {

    public static final MidNum INSTANCE = new MidNum();

    private PriorityQueue<Integer> bigHeap = new PriorityQueue<>(50, (o1, o2) -> {
       if(o1 < o2){
           return 1;
       } else if(o1 > o2){
           return -1;
       }
       return 0;
    });

    private PriorityQueue<Integer> smallHeap = new PriorityQueue<>(50);

    private int count;

    public void putNum(int num){
        count++;
        if(bigHeap.isEmpty() && smallHeap.isEmpty()){
            bigHeap.offer(num);
            return;
        }

        if(bigHeap.peek() < num){
            smallHeap.offer(num);
        }else {
            bigHeap.offer(num);
        }

        int halfCount = count / 2;
        if(bigHeap.size() > halfCount){
            move(bigHeap, smallHeap, bigHeap.size() - halfCount);
            return;
        }

        if(smallHeap.size() > halfCount){
            move(smallHeap, bigHeap, smallHeap.size() - halfCount);
            return;
        }

    }

    public int getMidNum(){
        return bigHeap.peek();
    }


    private void move(PriorityQueue<Integer> src, PriorityQueue<Integer> target, int num){
        for(int i = 0; i < num; i++){
            target.offer(src.poll());
        }
    }

    public static void main(String[] args) {
        MidNum midNum = MidNum.INSTANCE;
        midNum.putNum(0);
        midNum.putNum(1);
        midNum.putNum(2);
        midNum.putNum(3);
        midNum.putNum(4);
        midNum.putNum(5);
        midNum.putNum(6);
        midNum.putNum(7);
        midNum.putNum(8);
        System.out.println(midNum.getMidNum());

    }

}
