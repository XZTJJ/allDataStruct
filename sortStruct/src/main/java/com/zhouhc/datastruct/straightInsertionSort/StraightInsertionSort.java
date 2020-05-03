package com.zhouhc.datastruct.straightInsertionSort;

/**
 * 插入排序: 插入排序是将一个记录插入到已经排好序的有序表中，
 * 从而得到一个新的记录数加1的有序表
 */
public class StraightInsertionSort {

    //插入排序算法
    public static Integer[] sort(Integer[] sortArray) {
        int value = -2;
        int j = -9;
        //插入排序算法
        for (int i = 1; i < sortArray.length; i++) {
            //证明需要排序了，如果存在这种情况
            if (sortArray[i] < sortArray[i - 1]) {
                //保存要比较的值,设置要备份的值
                value = sortArray[i];
                //原来排好序的数据一直后移，直到找到对value小的数为止
                for (j = i - 1;  j > -1 && sortArray[j] > value ; j--)
                    sortArray[j + 1] = sortArray[j];
                //对原来的数进行赋值操作
                sortArray[j+1] = value;
            }
        }
        return sortArray;
    }
}
