package com.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 对数器： 随机选择10000个数，使用系统标志算法排序与自定义算法比较结果，循环N次，返回结果。
 */
public class SortAlgorithmCheckUtil {


    public static int[] createRandomArray(){
        int[] array = new int[10000];
        Random random = new Random();
        for(int i = 0; i < array.length; i++){
            array[i] = random.nextInt();
        }
        return array;
    }

    public static boolean check(){
        int[] arrayStandard = createRandomArray();
        int[] arrayCustom = arrayStandard.clone();

        Arrays.sort(arrayStandard);

//        SelectionSort.sort(arrayCustom);
//        BubbleSort.sort(arrayCustom);
        ShellSort.sort(arrayCustom);

        for(int i = 0; i < arrayStandard.length; i++){
            if(arrayStandard[i] != arrayCustom[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++){
            if (!check()){
                System.out.println(false);
                break;
            }
        }
        System.out.println(true);
    }
}
