package com.zhouhc.datastruct.sequencesearch;

import com.zhouhc.datastruct.avltree.AvlTreeTest;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

//普通算法，数组有序比较
public class SequenceSearch {
    private static Logger LOG = LoggerFactory.getLogger(SequenceSearch.class);

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
        LOG.info("数组为: [ " + StringUtils.join(integerList.toArray(), ", ") + " ]");
        LOG.info("查找关键字为 : " + keyWord + ", 找到的下标索引为: " + index);
        //第二次查找
        keyWord = integerList.get(0);
        index = indexOfKey(integerList, keyWord);
        LOG.info("数组为: [ " + StringUtils.join(integerList.toArray(), ", ") + " ]");
        LOG.info("查找关键字为 : " + keyWord + ", 找到的下标索引为: " + index);
    }

    //普通算法，数组有序比较,逐一比较
    private int indexOfKey(List<Integer> integerList, int keyWord) {
        for (int i = 0; i < integerList.size(); i++)
            if (integerList.get(i) == keyWord)
                return i;
        return -1;
    }
}
