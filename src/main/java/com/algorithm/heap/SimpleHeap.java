package com.algorithm.heap;

/**
 * 手写一个简易堆
 * 堆：1、完全二叉树
 *     2、堆中的每一个节点都必须大于等于（或者小于等于）其子树每一个节点的值
 *
 */
public class SimpleHeap {

    // 数组，从下标1开始存储数据
    private int[] array;
    // 堆可以存储的最大数据的个数
    private int n;
    // 堆中已经存储的数据个数
    private int count;

    public SimpleHeap(int capacity) {
        array = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    public void insert(int data){
        if(count >= n){
            return;
        }
        ++count;
        array[count] = data;
        int i = count;
        // 自下往上堆化
        while(i / 2 > 0 && array[i] > array[i / 2]){
            swap(array, i, i / 2);
            i = i / 2;
        }
    }

    public void removeMax(){
        if (count == 0){
            return;
        }
        array[1] = array[count];
        --count;
        heapify(array, count, 1);
    }

    /**
     * 自上往下堆化
     * @param a
     * @param n
     * @param i
     */
    private void heapify(int[] a, int n, int i){
        while (true){
            int maxPos = i;
            if(i*2 <= n && a[i] < a[i*2]){
                maxPos = i * 2;
            }
            if(i*2+1 <= n && a[maxPos] < a[i*2+1]){
                maxPos = i*2+1;
            }
            if(maxPos == i){
                break;
            }
            swap(a, i, maxPos);
            i = maxPos;
        }

    }

    private void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
