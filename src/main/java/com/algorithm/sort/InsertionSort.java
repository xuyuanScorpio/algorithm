package com.algorithm.sort;

/**
 * 插入排序：通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 */
public class InsertionSort {

    private static int[] array = {8, 9 ,2 ,7 ,6 ,4 ,5 ,1 ,3};

    public static void sort(int[] array){

        int length = array.length;
        for(int i  = 1; i < length; i++){
            int preIndex = i - 1;
            int current = array[i];
            while (preIndex >= 0 && array[preIndex] > current){
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
            SortUtil.printArray(array);
            System.out.println();

        }
    }



    public static void main(String[] args) {

        sort(array);
//        SortUtil.printArray(array);

    }


}
