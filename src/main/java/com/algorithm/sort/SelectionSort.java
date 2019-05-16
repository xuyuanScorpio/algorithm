package com.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，
 *          然后放到已排序序列的末尾
 */
public class SelectionSort {

    private static int[] array = {8, 9 ,2 ,7 ,6 ,4 ,5 ,1 ,3};

    public static void sort(int[] array){

        for(int i = 0; i < array.length - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < array.length ; j++){
                if(array[j] < array[minIndex]){
                    minIndex = j;
                }
            }
            SortUtil.swap(array, i, minIndex);
        }

    }


    public static void main(String[] args) {

        sort(array);
        SortUtil.printArray(array);

    }


}
