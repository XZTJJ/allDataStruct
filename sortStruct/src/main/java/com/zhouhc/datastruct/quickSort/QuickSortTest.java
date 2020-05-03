package com.zhouhc.datastruct.quickSort;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//快速排序的测试数据方式
public class QuickSortTest {
    private static Logger LOG = LoggerFactory.getLogger(QuickSortTest.class);

    //测试归并函数
    @Test
    public void testMergingSort() {
        for (int i = 1; i <= 20; i++)
            quickSort(i);

    }

    private void  quickSort(int count) {
        //排序数据
        List<Integer> integerList = new ArrayList<Integer>();
        //产生20个随机数，不重复的
        Random random = new Random();
        while (integerList.size() < count)
            integerList.add(random.nextInt(100));
        Integer[] sortingArrays = integerList.toArray(new Integer[1]);
        Integer[] testV2 = integerList.toArray(new Integer[1]);
        LOG.info("原数组:" + Arrays.toString(sortingArrays));
        sortingArrays = QuickSort.RSort(sortingArrays);
        testV2 = QuickSort.ISort(testV2);
        LOG.info("递归版本排序好的数组为:" + Arrays.toString(sortingArrays));
        LOG.info("迭代版本排序好的数组为:" + Arrays.toString(testV2));
        LOG.info("对排好序的数组进行排序");
        sortingArrays = QuickSort.RSort(sortingArrays);
        testV2 = QuickSort.ISort(testV2);
        LOG.info("递归版本排序好的数组为:" + Arrays.toString(sortingArrays));
        LOG.info("迭代版本排序好的数组为:" + Arrays.toString(testV2));

    }
}
