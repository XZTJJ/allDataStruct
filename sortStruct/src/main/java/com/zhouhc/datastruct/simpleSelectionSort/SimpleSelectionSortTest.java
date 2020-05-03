package com.zhouhc.datastruct.simpleSelectionSort;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//测试
public class SimpleSelectionSortTest {
    private static Logger LOG = LoggerFactory.getLogger(SimpleSelectionSortTest.class);

    @Test
    public void testSimpleSelectionSort(){
        simpleSelectionSort(20);
        simpleSelectionSort(21);
    }

    public void simpleSelectionSort(int count){
        //排序数据
        List<Integer> integerList = new ArrayList<Integer>();
        //产生20个随机数，不重复的
        Random random = new Random();
        while (integerList.size() < count)
            integerList.add(random.nextInt(100));
        Integer[] sortingArrays = integerList.toArray(new Integer[1]);
        LOG.info("原数组:"+ Arrays.toString(sortingArrays));
        sortingArrays = SimpleSelectionSort.sort(sortingArrays);
        LOG.info("排好序的数组为:"+Arrays.toString(sortingArrays));
        LOG.info("对排好序的数组进行排序:");
        sortingArrays = SimpleSelectionSort.sort(sortingArrays);
        LOG.info("排好序的数组为:"+Arrays.toString(sortingArrays));
    }

}
