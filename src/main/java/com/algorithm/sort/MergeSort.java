package com.algorithm.sort;

import java.util.HashMap;
import java.util.Map;

/**
 * 归并排序
 * 思考：如何将哨兵引入 merge方法简化代码
 */
public class MergeSort {

    public void mergeSort(int[] array, int n){
        mergeSortInternally(array, 0, n-1);
    }


    public void mergeSortInternally(int[] array, int p, int r){
        if(p >= r){
            return;
        }
        int q = p + ((r - p) >> 1);

        // 分治递归
        mergeSortInternally(array, p, q);
        mergeSortInternally(array, q + 1, r);

        merge(array, p, q, r);
    }


    private void merge(int[] a, int p, int q, int r){

//        int[] result = new int[a.length + b.length];
//        int aStart = 0;
//        int bStart = 0;
//        int index = 0;
//        while(aStart < a.length && bStart < b.length){
//            if(a[aStart] < b[bStart]){
//                result[index] = a[aStart];
//                aStart++;
//            }else{
//                result[index] = b[bStart];
//                bStart++;
//            }
//            index++;
//        }
//        // 判断哪个数据中有剩余数组，将剩余数组拷贝到结果中
//        if(aStart < a.length){
//            for(int i = aStart; i < a.length; i++){
//                result[index] = a[i];
//                index++;
//            }
//        }
//
//        if(bStart < b.length){
//            for(int i = bStart; i < b.length; i++){
//                result[index] = b[i];
//                index++;
//            }
//        }
//        return result;

        int i = p;
        int j = q + 1;
        int k = 0;
        int[] temp = new int[r - p + 1];
        // 合并
        while (i <= q && j <= r){
            if(a[i] <= a[j]){
                temp[k++] = a[i++];
            }else {
                temp[k++] = a[j++];
            }
        }
        // 判断哪个子数组中有剩余的数据
        int start = i;
        int end = q;
        if(j <= r){
            start = j;
            end = r;
        }
        // 将剩余的数据拷贝到临时数组tmp
        while(start <= end){
            temp[k++] = a[start++];
        }
        // 将tmp中的数组拷贝会a[p...r]
        for(i = 0; i <= r-p; ++i){
            a[p+i] = temp[i];
        }

    }

    public void printAll(int[] array){
        for(int i = 0; i < array.length; i++){
            System.out.println(array[i] + "  ");
        }
    }

    public static void main(String[] args) {

//        int[] array = {11, 8, 3, 9, 7, 1, 2, 5};
//
//        MergeSort mergeSort = new MergeSort();
//        mergeSort.mergeSort(array, 8);
//
//        mergeSort.printAll(array);



    }
}
