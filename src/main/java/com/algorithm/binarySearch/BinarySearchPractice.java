package com.algorithm.binarySearch;

import java.math.BigDecimal;

/**
 * 1、实现求一个数的平方根，要求精确到小数点后6位
 *
 * 二分法的变形问题：
 * 1、查找第一个值等于给定值的元素
 * 2、查找最后一个值等于给定值的元素
 * 3、查找第一个大于等于给定值的元素
 * 4、查找最后一个小于等于给定值的元素
 *
 * 思考：如果一个有序数组是一个循环有序数组，eg:4, 5, 6, 1, 2, 3 如何实现一个求
 *      "值等于给定值"的二分查找算法
 *
 */
public class BinarySearchPractice {

    public double sqrt(double x, double precision){

        if(x < 0){
            return Double.NaN;
        }
        double low = 0;
        double high = x;
        if(x < 1 && x > 0){
            low = x;
            high = 1;
        }
        double mid = low + (high - low) / 2;
        while(high - low > precision){
            if(mid * mid > x){
                high = mid;
            }else if(mid * mid < x){
                low = mid;
            }else {
                return mid;
            }
            mid = low + (high - low) / 2;
        }
        return new BigDecimal(mid).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 查找第一个值等于给定值的元素
     * @param array
     * @param n
     * @param value
     * @return
     */
    public int bsearch1(int[] array, int n, int value){

        int low = 0;
        int high = n - 1;
        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(array[mid] > value){
                high = mid - 1;
            }else if(array[mid] < value){
                low = mid + 1;
            }else {
                if((mid == 0) || (array[mid - 1] != value)){
                    return mid;
                }else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素
     * @param array
     * @param n
     * @param value
     * @return
     */
    public int bsearch2(int[] array, int n, int value){
        int low = 0;
        int high = n - 1;
        while (low <= high){
            int mid = low + ((high - low) >> 1);
            if(array[mid] > value){
                high = mid - 1;
            }else if(array[mid] < value){
                low = mid + 1;
            }else {
                if((mid == n - 1) || (array[mid + 1] != value)){
                    return mid;
                }else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }


    /**
     * 查找第一个大于等于给定值的元素
     * @param array
     * @param n
     * @param value
     * @return
     */
    public int bsearch3(int[] array, int n, int value){
        int low = 0;
        int high = n - 1;
        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(array[mid] >= value){
                if((mid == 0) || (array[mid - 1] < value)){
                    return mid;
                }else {
                    high = mid - 1;
                }
            }else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     * @param array
     * @param n
     * @param value
     * @return
     */
    public int bsearch4(int[] array, int n, int value){
        int low = 0;
        int high = n - 1;
        while (low <= high){
            int mid = low + ((high - low) >> 1);
            if(array[mid] > value){
                high = mid - 1;
            }else {
                if ((mid == n - 1) || (array[mid + 1] > value)){
                    return mid;
                }else {
                    low = mid + 1;
                }
            }
        }
       return -1;
    }

    public static void main(String[] args) {
        BinarySearchPractice practice = new BinarySearchPractice();
//        System.out.println(practice.sqrt(16d, 0.000001));
        System.out.println(practice.bsearch1(practice.array, 9, 8));
        System.out.println(practice.bsearch2(practice.array, 9, 8));
        System.out.println(practice.bsearch3(practice.array, 9, 8));
        System.out.println(practice.bsearch4(practice.array, 9, 8));

    }


    private int[] array = {1, 3, 4, 5, 8, 8, 8, 11, 18};

}
