package com.algorithm.sort;

/**
 * 快速排序
 * 优化：双轴快排
 */
public class QuickSort {



    public void quickSort(int[] array, int n){
        quickSortInternally(array, 0, n - 1);
    }

    public void quickSortInternally(int[] array, int p, int r){
        if(p >= r){
            return;
        }
        // 获取分区点
        int q = partition(array, p, r);

        // 分治递归
        quickSortInternally(array, p, q - 1);
        quickSortInternally(array, q + 1, r);

    }

    private int partition(int[] array, int p, int r){
        // 选择最后一个数作为分区点
        int pivot = array[r];
        int i = p;
        for(int j = p; j < r; ++j){
            if(array[j] < pivot){
                if(i == j){
                    ++i;
                }else {
                    int temp = array[i];
                    array[i++] = array[j];
                    array[j] = temp;
                }
            }
        }

        int temp = array[i];
        array[i] = array[r];
        array[r] = temp;
//        System.out.println(i);
        return i;
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


    public static void main(String[] args) {
        int[] array = {7, 3, 2, 8, 1, 9, 5, 4, 6};

        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(array, 9);
        quickSort.print(array);

    }

}
