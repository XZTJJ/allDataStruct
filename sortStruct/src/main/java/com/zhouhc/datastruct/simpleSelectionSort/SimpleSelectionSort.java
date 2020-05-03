package com.zhouhc.datastruct.simpleSelectionSort;

/**
 * 选择排序的核心思想是: 对n个关键字，进行n-1次比较
 * ，从n -i+1 中选择关键字最小的记录，和下标为i的元素互换
 * 没有优化的方式
 */
public class SimpleSelectionSort {
    //开始比较
    public static Integer[] sort(Integer[] sortingArrays) {
        //进行排序
        for (int i = 0; i < sortingArrays.length; i++) {
            //进行比较
            for(int j = i+1 ;j<sortingArrays.length;j++){
                //找到最小值
                if(sortingArrays[i] > sortingArrays[j]){
                    sortingArrays[j] = sortingArrays[i] + sortingArrays[j];
                    //小的放前面
                    sortingArrays[i] = sortingArrays[j] - sortingArrays[i];
                    //大的放后面
                    sortingArrays[j] = sortingArrays[j] - sortingArrays[i];
                }
            }
        }
        return sortingArrays;
    }
}
