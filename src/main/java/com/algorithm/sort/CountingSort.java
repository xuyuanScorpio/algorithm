package com.algorithm.sort;

/**
 * 计数排序
 */
public class CountingSort {

    public void countingSort(int[] array, int n){
        if(n <= 1){
            return;
        }

        // 查找数组中数据的范围
        int max = array[0];
        for(int i = 1; i < n; ++i){
            if(max < array[i]){
                max = array[i];
            }
        }

        // 申请一个计数数组c, 下标大小为[0, max]
        int[] c = new int[max + 1];
        for(int i = 0; i < max + 1; ++i){
            c[i] = 0;
        }

        // 计算每个元素的个数，放入c
        for(int i = 0; i < n; ++i){
            c[array[i]]++;
        }

        // 依次累加
        for(int i = 1; i < max + 1; ++i){
            c[i] = c[i - 1] + c[i];
        }

        // 临时数组r, 存储排序后的结果
        int[] r = new int[n];
        for(int i = n - 1; i >= 0; --i){
            int index = c[array[i]] - 1;
            r[index] = array[i];
            c[array[i]]--;
        }

        // 将结果拷贝回array数组
        for(int i = 0; i < n; ++i){
            array[i] = r[i];
        }
    }

}
