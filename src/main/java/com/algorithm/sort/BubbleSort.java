package com.algorithm.sort;

/**
 * 冒泡排序：   冒泡排序的名字很形象，实际实现是相邻两节点进行比较，大的向后移一个，经过第一轮两两比较和移动，最大的元素移动到了最后，
 *             第二轮次大的位于倒数第二个，依次进行
 */
public class BubbleSort {


    private static int[] array = {8, 9 ,2 ,7 ,6 ,4 ,5 ,1 ,3};

    public static void sort(int[] array){

        int lenth = array.length;
        for(int i = 0; i < lenth - 1; i++){
            for(int j = 0; j < array.length - i - 1; j++){
                if(array[j] > array[j + 1]){
                    SortUtil.swap(array, j, j + 1);
                }
            }
        }
    }

    public static void compare(){
        for(int i = 0; i < array.length - 1; i++){
            if(array[i] > array[i + 1]){
                SortUtil.swap(array, i, i + 1);
            }
        }
    }


    public static void main(String[] args) {

        sort(array);
        SortUtil.printArray(array);

    }


}
