package com.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 基数排序
 *
 */
public class RadixSort {

    private static ArrayList[] bucket = new ArrayList[10];

    static {
        for(int i = 0; i < bucket.length; i++){
            bucket[i] = new ArrayList();
        }
    }


    /**
     *
     * 将数组 arr, 按d这个位来分配和收集
     * @param array
     * @param d
     */
    public static void sort(int[] array, int d){

        for(int i = 0; i < array.length; i++){
            putInBucket(array[i], getDigitOn(array[i], d));
        }

        // 每个桶的数据依次压入原数组
        int k = 0;
        for(int j = 0; j < bucket.length; j++){
            for(Object obj : bucket[j]){
                array[k++] = (Integer) obj;
            }
        }
        // 完成排序后清空桶
        clearAll();
    }

    private static void putInBucket(int data, int digitOn){
        switch (digitOn){
            case 0:
                bucket[0].add(data);
                break;
            case 1:
                bucket[1].add(data);
                break;
            case 2:
                bucket[2].add(data);
                break;
            case 3:
                bucket[3].add(data);
                break;
            case 4:
                bucket[4].add(data);
                break;
            case 5:
                bucket[5].add(data);
                break;
            case 6:
                bucket[6].add(data);
                break;
            case 7:
                bucket[7].add(data);
                break;
            case 8:
                bucket[8].add(data);
                break;
            default:
                bucket[9].add(data);
                break;
        }
    }

    private static void clearAll(){
        for(ArrayList b : bucket){
            b.clear();
        }
    }

    public static void sort(int[] array){
        // 入桶依据的位初始化为1
        int d = 1;
        int max = array[0];
        for(int i = 1; i < array.length; i++){
            if(array[i] > max){
                max = array[i];
            }
        }

        int dNum = 1;
        while(max / 10 != 0){
            dNum++;
            max /= 10;
        }

        while (d <= dNum){
            sort(array, d++);
        }
    }

    public static int getDigitOn(int src, int d){
        return src / (int)(Math.pow(10, d - 1)) % 10;
    }

    public static void main(String[] args) {
        int array[] = new int[10];
        for(int i= 0; i < 10; i++){
            array[i] = (int) ((Math.random() + 1) * 10);
        }
        System.out.println("排序前： " + Arrays.toString(array));
        sort(array);
        System.out.println("排序后： " + Arrays.toString(array));
    }

}
