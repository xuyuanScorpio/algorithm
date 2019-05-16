package com.algorithm.sort;

/**
 * 希尔排序:将需要排序的序列划分成为若干个较小的子序列，对子序列进行插入排序，通过则插入排序能够使得原来序列成为基本有序
 * Knuth序列 : h = 3*h + 1
 *
 */
public class ShellSort {


    private static int[] array = {8, 9 ,2 ,7 ,6 ,4 ,5 ,1 ,3};

    public static void sort(int[] array){

        for(int gap = array.length / 2; gap > 0; gap /= 2){
            for(int i  = gap; i < array.length; i++){
                int j = i;
                int temp = array[j];
                if(array[j] < array[j - gap]){
                    while(j - gap >= 0 && temp < array[j-gap]){
                        array[j] = array[j - gap];
                        j -= gap;
                    }
                }
                array[j] =temp;

//                SortUtil.printArray(array);
//                System.out.println();
            }

        }


    }


    public static void main(String[] args) {

        sort(array);


    }



}
