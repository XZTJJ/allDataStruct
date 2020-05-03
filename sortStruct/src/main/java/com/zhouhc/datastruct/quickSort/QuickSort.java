package com.zhouhc.datastruct.quickSort;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 快速排序，每次遍历，选取一个值，一次遍历结束后
 * 该值找到合适的为止，并且该值左边的数要小于等于该值，
 * 该值右边的数要大于等于该值
 */
public class QuickSort {

    //递归版本的快速排序
    public static Integer[] RSort(Integer[] sortingArrays) {
        //调用方法
        RSortDetail(sortingArrays, 0, sortingArrays.length - 1);
        return sortingArrays;
    }

    //递归版本的快速排序详细实现
    private static void RSortDetail(Integer[] sortingArrays, int low, int high) {
        //循环结束的条件就是，数组只剩下一个元素没有排序了
        while (low < high) {
            //某个已经确定位置的值的下标
            int postion = getPostion(sortingArrays, low, high);
            //需要一直迭代,只要到只有一个元素为止
            RSortDetail(sortingArrays, low, postion - 1);
            //现在是右边的迭代
            low = postion + 1;
        }
    }

    //某个值找到确定位置的具体方法
    private static int getPostion(Integer[] sortingArrays, int low, int high) {
        Integer tempValue = getMiddleValue(sortingArrays, low, high);
        //开始循环比较，只要当low等于high时，才表示找到了对应的位置
        while (low < high) {
            //先从右往前找比tempValue小的，直接替换sortingArrays[low] 的值就好，有备份不用担心
            while (high > low && sortingArrays[high] >= tempValue)
                high--;
            //较小的值放到左边
            sortingArrays[low] = sortingArrays[high];
            //现在需要从前往后找比tempValue大的，这样就可以不用互换了，直接替换sortingArrays[high] 就好
            while (low < high && sortingArrays[low] <= tempValue)
                low++;
            //位置互换
            sortingArrays[high] = sortingArrays[low];
        }
        //循环结束，这个时候应该Low == high才对，现在需要将tempValue放入合适的位置
        sortingArrays[low] = tempValue;
        return low;
    }

    //获取中间值，为了性能方面考虑，一般是三个数取中间的那个数
    private static Integer getMiddleValue(Integer[] sortingArrays, int low, int high) {
        //临时下标
        int middle = (low + high) / 2;
        //选取最左边和最右边的数进行比较，保证最左边的数的值比较小
        if (sortingArrays[low] > sortingArrays[high])
            swapValue(sortingArrays, low, high);
        //选取中间数和最右边的数进行比较，保证中间的数的值比较小
        if (sortingArrays[middle] > sortingArrays[high])
            swapValue(sortingArrays, middle, high);
        //最左边的数和中间数进行比较，保证最左边的数的数值大小是处于中间的
        if (sortingArrays[middle] > sortingArrays[low])
            swapValue(sortingArrays, middle, low);
        return sortingArrays[low];
    }

    //两个数互换位置
    private static void swapValue(Integer[] sortingArrays, int lowIndex, int highIndex) {
        int tempValue = sortingArrays[highIndex];
        sortingArrays[highIndex] = sortingArrays[lowIndex];
        sortingArrays[lowIndex] = tempValue;
    }

    //非递归版本
    public static Integer[] ISort(Integer[] sortingArrays) {
        //队列，用于保存每次分割的数组的大小的边界
        Queue<Integer> integersQueue = new LinkedBlockingQueue<Integer>();
        //初始化队列
        if (sortingArrays.length > 1) {
            integersQueue.add(0);
            integersQueue.add(sortingArrays.length - 1);
        }
        //用于记录下标
        int start, end;
        //循环终止的条件是integersQueue没有待分割的数组的大小了
        while (integersQueue.size() > 0) {
            //获取比较的开始和结束边界的坐标值
            start = integersQueue.remove();
            end = integersQueue.remove();
            //证明只有一个元素，不用比较直接跳出
            if (start >= end)
                continue;
            //开始对某个元素定位
            int postion = getPostion(sortingArrays, start, end);
            //将下一次开始时间和结束的标识放入队列中
            integersQueue.add(start);
            integersQueue.add(postion - 1);
            integersQueue.add(postion + 1);
            integersQueue.add(end);
        }
        return sortingArrays;
    }
}
