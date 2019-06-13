package com.algorithm.heap.solution.topK;

import java.util.PriorityQueue;

public class TopK {

    /**
     * 获取数据中的 topK
     * @param data
     * @param k
     * @return
     */
    public int[] topK(int[] data, int k){

        PriorityQueue<Integer> queue = new PriorityQueue<>(k);

        for(int i = 0; i < data.length; i++){
            if(queue.size() < k){
                queue.offer(data[i]);
            }else {
                int value = queue.peek();
                // 如果发现数据比堆顶元素大，则加入到小顶堆中
                queue.poll();
                if(data[i] > value){
                    queue.offer(data[i]);
                }
            }
        }
        int[] result = new int[k];
        int index = 0;
        while(!queue.isEmpty()){
            result[index++] = queue.poll();
        }
        return result;
    }



}
