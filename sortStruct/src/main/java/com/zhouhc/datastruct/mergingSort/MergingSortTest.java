package com.zhouhc.datastruct.mergingSort;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MergingSortTest {

    private static Logger LOG = LoggerFactory.getLogger(MergingSortTest.class);

    //测试归并函数
    @Test
    public void testMergingSort() {
        for (int i = 1; i <= 100; i++)
            mergingSort(i);

    }

    private void mergingSort(int count) {
        //排序数据
        List<Integer> integerList = new ArrayList<Integer>();
        //产生20个随机数，不重复的
        Random random = new Random();
        while (integerList.size() < count)
            integerList.add(random.nextInt(100));
        Integer[] sortingArrays = integerList.toArray(new Integer[1]);
        Integer[] testV2 = integerList.toArray(new Integer[1]);
        LOG.info("原数组:" + Arrays.toString(sortingArrays));
        sortingArrays = MergingSort.rSort(sortingArrays);
        testV2 = MergingSort.iSort(testV2);
        LOG.info("递归版本排序好的数组为:" + Arrays.toString(sortingArrays));
        LOG.info("迭代版本排序好的数组为:" + Arrays.toString(testV2));
        LOG.info("对排好序的数组进行排序");
        sortingArrays = MergingSort.rSort(sortingArrays);
        testV2 = MergingSort.iSort(testV2);
        LOG.info("递归版本排序好的数组为:" + Arrays.toString(sortingArrays));
        LOG.info("迭代版本排序好的数组为:" + Arrays.toString(testV2));

    }

}
