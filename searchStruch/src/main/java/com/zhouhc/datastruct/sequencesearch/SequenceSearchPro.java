package com.zhouhc.datastruct.sequencesearch;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//朴素算法的优化方式，移除判断数组越界的步骤
public class SequenceSearchPro {
    private static Logger LOG = LoggerFactory.getLogger(SequenceSearchPro.class);

    @Test
    public void testSequenceSearch() {
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
        //索引下标
        int index = indexOfKey(integerList, keyWord);
        integerList = integerList.subList(1, integerList.size());
        LOG.info("数组为: [ " + StringUtils.join(integerList.toArray(), ", ") + " ]");
        LOG.info("查找关键字为 : " + keyWord + ", 找到的下标索引为: " + index);
        //第二次查找
        keyWord = integerList.get(0);
        index = indexOfKey(integerList, keyWord);
        integerList = integerList.subList(1, integerList.size());
        LOG.info("数组为: [ " + StringUtils.join(integerList.toArray(), ", ") + " ]");
        LOG.info("查找关键字为 : " + keyWord + ", 找到的下标索引为: " + index);
    }

    //普通算法，数组有序比较,逐一比较
    private int indexOfKey(List<Integer> integerList, int keyWord) {
        //添加关键词
        integerList.add(0, keyWord);
        //下标记录
        int index = integerList.size() - 1;
        //循环比较
        while (integerList.get(index) != keyWord)
            index--;
        return index - 1;
    }
}
