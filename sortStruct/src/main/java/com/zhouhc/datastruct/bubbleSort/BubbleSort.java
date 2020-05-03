package com.zhouhc.datastruct.bubbleSort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 冒泡排序，冒泡排序的核心思想是：两两相互比较，如果大的就放在后面
 * 小的放在前面,一般是从后面往前面进行比较
 */
public class BubbleSort {
    private static Logger LOG = LoggerFactory.getLogger(BubbleSort.class);

    public static Integer[] sort(Integer[] sortingArray) {
        //用于标记数组是是否需要排序
        boolean isNeedSorted = true;
        //外层的循环
        for (int i = 0; i <= sortingArray.length && isNeedSorted; i++) {
            //修改成已经排好徐了
            isNeedSorted = false;
            //LOG.info("第" + (i+1) + "次循环");
            //内层的循环操作
            for (int j = sortingArray.length - 2; j >= i; j--) {
                //互换位置
                if (sortingArray[j] > sortingArray[j + 1]) {
                    sortingArray[j + 1] = sortingArray[j] + sortingArray[j + 1];
                    //小的放前面
                    sortingArray[j] = sortingArray[j + 1] - sortingArray[j];
                    //大的放后面
                    sortingArray[j + 1] = sortingArray[j + 1] - sortingArray[j];
                    //表示需要排序
                    isNeedSorted = true;
                }
            }
        }
        return sortingArray;
    }
}
