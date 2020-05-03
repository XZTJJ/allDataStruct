package com.zhouhc.datastruct.heapSort;

/**
 * 堆排序，堆排序使用的完全二叉树的特性，
 * 如果双亲结点比左右孩子都大，叫做大顶堆，
 * 如果双亲结点比左右孩子小，叫做小顶堆
 * 每次都将根结点和最后一个元素互换，
 * 重新构建堆，从而得到一个有序数组
 * <p>
 * 完全二叉树的特性 对于i结点而言，孩子为2i 和2i+1
 * 但是计算机中 表现出来的是i 的孩子为 2i+和2i+2，因为
 * 下标是从零开始的
 */

/**
 * 初始化一个大顶堆的过程是一个从右往左，从下往上的过程，双亲结点要和所有的结点比较，
 * 但是移除元素后调整大顶堆的过程是一个从上往下，从左往右的过程，双亲结点要和所有的结点比较，
 * 这是两个不同的过程,不过这两个过程都是和以孩子结点为基础的
 */
public class HeapSort {

    //排序方式
    public static Integer[] sortBigHeap(Integer[] sortingArrays) {
        //构建大顶堆,完成
        for (int i = sortingArrays.length / 2 - 1; i >= 0; i--)
            sortingArrays = adjustBigHeap(sortingArrays, i, sortingArrays.length);
        //开始调整
        for (int i = sortingArrays.length - 1; i > 0; i--) {
            //互换数据
            sortingArrays = swapBigValue(sortingArrays, i);
            //重新构建顶堆
            sortingArrays = adjustBigHeap(sortingArrays, 0, i);
        }

        return sortingArrays;
    }


    /**
     * 大顶堆的调整
     *
     * @param sortingArrays
     * @param length
     * @return
     */
    private static Integer[] adjustBigHeap(Integer[] sortingArrays, int index, int length) {
        //临时保存父结点
        int tempValue = sortingArrays[index];
        //游标作用
        int i = 2 * index + 1;
        //负责结点和孩子结点进行比较,length就是孩子结点的最大值
        while (i < length) {
            //左右孩子取最大值
            if ((i + 1) < length && sortingArrays[i] < sortingArrays[i + 1])
                i = i + 1;
            //表示父结点比孩子结点要小，父子结点互换位置
            if (tempValue > sortingArrays[i])
                break;
            sortingArrays[index] = sortingArrays[i];
            index = i;
            //找到下一个结点的左孩子
            i = 2 * i + 1;
        }
        sortingArrays[index] = tempValue;
        return sortingArrays;
    }

    /**
     * 将最大值移到最后
     */
    private static Integer[] swapBigValue(Integer[] sortingArrays, int length) {
        sortingArrays[0] = sortingArrays[0] + sortingArrays[length];
        sortingArrays[length] = sortingArrays[0] - sortingArrays[length];
        sortingArrays[0] = sortingArrays[0] - sortingArrays[length];
        return sortingArrays;
    }
}
