package com.algorithm.sort;

public class QuickSort {


    public static void main(String[] args) {
        int[] array = {7, 3, 2, 8, 1,9,5, 4, 6};

    }


    public void sort(int[] array, int leftBound, int rightBound){

    }

    private void partition(int[] array, int leftBound, int rightBound){

    }

    private void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void print(int[] array){
        for(int i = 0; i < array.length; i++){
            System.out.println(array[i] + " ");
        }
    }
}
