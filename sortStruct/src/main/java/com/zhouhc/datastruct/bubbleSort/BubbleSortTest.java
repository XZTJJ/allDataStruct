package com.zhouhc.datastruct.bubbleSort;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BubbleSortTest {
    private static Logger LOG = LoggerFactory.getLogger(BubbleSortTest.class);

    @Test
    public void testBubbleSort(){
        bubbleSort(20);
        bubbleSort(21);
    }

    public void bubbleSort(int count){
        //排序数据
        List<Integer> integerList = new ArrayList<Integer>();
        //产生20个随机数，不重复的
        Random random = new Random();
        while (integerList.size() < count)
            integerList.add(random.nextInt(100));
        Integer[] sortingArrays = integerList.toArray(new Integer[1]);
        LOG.info("原数组:"+ Arrays.toString(sortingArrays));
        sortingArrays = BubbleSort.sort(sortingArrays);
        LOG.info("排好序的数组为:"+Arrays.toString(sortingArrays));
        //
        LOG.info("对排好序的数组进行排序");
        sortingArrays = BubbleSort.sort(sortingArrays);
        LOG.info("排好序的数组为:"+Arrays.toString(sortingArrays));
    }

}
