package com.zhouhc.datastruct.binarySearch;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 插值查找算法，
 * 插值查找算法的前提是 : 数组必须有序的，才使用插值查找的方式
 * 核心思想: 插值查找的思想和折半查找的思想类似，不过不是中间值了，
 * 而是通过使用 类似于等比的性质来进行数据的查找，这种如果是在数据
 * 均匀分布的数组中，效率要比折半要高很多。核心就是等比的公式
 */
public class InterpolationSearch {
    private static Logger LOG = LoggerFactory.getLogger(InterpolationSearch.class);

    @Test
    public void testBinarySearch() {
        //排序数据
        List<Integer> integerList = new ArrayList<Integer>();
        //产生20个随机数，不重复的
        Random random = new Random();
        while (integerList.size() < 20) {
            int nextInt = random.nextInt(100);
            if (integerList.contains(nextInt))
                continue;
            integerList.add(nextInt);
        }
        //排序
        Collections.sort(integerList);
        //关键字
        int keyWord = integerList.get(random.nextInt(integerList.size()));
        LOG.info("数组为: [ " + StringUtils.join(integerList.toArray(), ", ") + " ]");
        //索引下标
        int index = indexOfKey(integerList, keyWord);
        LOG.info("查找关键字为 : " + keyWord + ", 找到的下标索引为: " + index);
        //第二次查找
        keyWord = integerList.get(0);
        LOG.info("数组为: [ " + StringUtils.join(integerList.toArray(), ", ") + " ]");
        index = indexOfKey(integerList, keyWord);
        LOG.info("查找关键字为 : " + keyWord + ", 找到的下标索引为: " + index);
    }

    //二分查找算法的核心
    private int indexOfKey(List<Integer> integerList, int keyWord) {
        //数组最后元素的下标
        int high = integerList.size() - 1;
        int low = 0;
        //中间值
        int middle = -1;
        //下标索引
        int index = -1;
        //开始比较，结束的条件是low大于High
        while (low <= high) {
            //始终取等比例的值
            middle = low + (high - low) * (keyWord - integerList.get(low)) /
                    (integerList.get(high) - integerList.get(low));
            int middleDataKey = integerList.get(middle);
            //比较元素
            LOG.info("中间值为:" + middleDataKey + "，关键字是:" + keyWord);
            //找到值了就直接返回
            if (middleDataKey == keyWord)
                return middle;
            //如果中间值大于关键字，则在左边找
            if (middleDataKey > keyWord)
                high = middle - 1;
                //如果中间值小于关键字，则在右边找
            else
                low = middle + 1;
        }

        return index;
    }
}
