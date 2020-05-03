package com.zhouhc.datastruct.heapSort;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HeapSortTest {
    private static Logger LOG = LoggerFactory.getLogger(HeapSortTest.class);

    @Test
    public void testHeapSort() {
        //Integer[] testSort = new Integer[]{50,10,90,30,70,40,80,60,20};
        //testSort = HeapSort.sortBigHeap(testSort);

        for(int i =1;i<=100;i++)
            heapSort(i);
    }

    public void heapSort(int count) {
        //排序数据
        List<Integer> integerList = new ArrayList<Integer>();
        //产生20个随机数，不重复的
        Random random = new Random();
        while (integerList.size() < count)
            integerList.add(random.nextInt(100));
        Integer[] sortingArrays = integerList.toArray(new Integer[1]);
        LOG.info("原数组:" + Arrays.toString(sortingArrays));
        sortingArrays = HeapSort.sortBigHeap(sortingArrays);
        LOG.info("排序好的数组为:" + Arrays.toString(sortingArrays));
        LOG.info("对排好序的数组进行排序");
        sortingArrays = HeapSort.sortBigHeap(sortingArrays);
        LOG.info("排序序的数组为:" + Arrays.toString(sortingArrays));
    }
}
