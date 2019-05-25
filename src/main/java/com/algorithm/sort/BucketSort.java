package com.algorithm.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 桶排序:将要排序的数据分到几个有序的桶里，每个桶里的数据在单独进行排序。桶内排完序后，在把每个桶里的数据按照顺序依次取出，组成的序列就是有序的了。
 * eg： 待排数组  22, 11, 23,  38 , 12,  35
 * 思路：可以分为3个桶  桶1（10-20）桶2（20-30）桶3（30-40）
 *      遍历数组 将数组中元素对应入桶
 *      每个桶在单独排序，最后在依次取出
 *
 */
public class BucketSort {

    public void bucketSort(int[] array, List<ArrayList> buckets){

        if(array.length == 0){
            return;
        }
        putInBucket(array, buckets);
        bucketInnerSort(buckets);
        takeOutAndPutInArray(array, buckets);


    }

    /**
     * 遍历将原数组，放入对应的桶中
     * @param array
     * @param buckets
     */
    public void putInBucket(int[] array, List<ArrayList> buckets){
        if(array.length == 0 || buckets.size() == 0){
            return;
        }
        for(int i = 0; i < array.length; i++){
            if(array[i] > 10 && array[i] < 20){
                buckets.get(0).add(array[i]);
            }
            if(array[i] > 20 && array[i] < 30){
                buckets.get(1).add(array[i]);
            }
            if(array[i] > 30 && array[i] < 40){
                buckets.get(2).add(array[i]);
            }
        }
    }

    /**
     * 桶内元素排序
     * @param buckets
     */
    public void bucketInnerSort(List<ArrayList> buckets){

        if(buckets.size() == 0){
            return;
        }
        for(ArrayList bucket : buckets){
            Collections.sort(bucket);
        }
    }

    /**
     * 依次取出桶内元素并回填入原数组中
     * @param array
     * @param buckets
     */
    public void takeOutAndPutInArray(int[] array, List<ArrayList> buckets){
        int index = 0;

        for(ArrayList bucket : buckets){
            for(int i = 0; i < bucket.size(); i++){
                array[index++] = (Integer) bucket.get(i);
            }
        }
    }

    public void printAll(int[] array){
        for(int i = 0; i < array.length; i++){
            System.out.println(array[i] + "  ");
        }
    }

    public static void main(String[] args) {
        int[] array = {22, 11, 23,  38 , 12,  35};
        List<ArrayList> buckets = new ArrayList<ArrayList>(3);
        buckets.add(new ArrayList());
        buckets.add(new ArrayList());
        buckets.add(new ArrayList());
        BucketSort bucketSort = new BucketSort();
        bucketSort.bucketSort(array, buckets);
        bucketSort.printAll(array);
    }

}
