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
 * 斐波那挈查找
 * 斐波那挈查找需要借助斐波那挈数列，
 * 其核心核心思想是通过斐波那挈的黄金比例来进行对数据的分割查找
 * f(n) = f(n-1) + f(n-2)  其中f(n)是由f(n-1)个前面的数据和
 * f(n-2)个后面的数据组组成的。
 * 查找就是通过找最接近数组长度但是要比数组长度大的那个斐波那挈数列的元素
 * 一直循环查找整个元素
 */
public class FibonacciSearch {

    private static Logger LOG = LoggerFactory.getLogger(InterpolationSearch.class);

    @Test
    public void testFibonacciSearch() {
        //排序数据
        List<Integer> integerList = new ArrayList<Integer>();
        //斐波那挈数组
        List<Integer> fibonacciList = null;
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
        //构建斐波那挈数列
        fibonacciList = (List<Integer>) createFibonacci(integerList.size());
        //关键字
        int keyWord = integerList.get(random.nextInt(integerList.size()));
        LOG.info("数组为: [ " + StringUtils.join(integerList.toArray(), ", ") + " ]");
        //索引下标
        int index = indexOfKey(integerList, keyWord, fibonacciList);
        LOG.info("查找关键字为 : " + keyWord + ", 找到的下标索引为: " + index);
        //第二次查找
        keyWord = integerList.get(0);
        LOG.info("数组为: [ " + StringUtils.join(integerList.toArray(), ", ") + " ]");
        index = indexOfKey(integerList, keyWord, fibonacciList);
        LOG.info("查找关键字为 : " + keyWord + ", 找到的下标索引为: " + index);
    }

    //二分查找算法的核心
    private int indexOfKey(List<Integer> integerList, int keyWord, List<Integer> fibonacciList) {

        //数组最后元素的下标
        int high = integerList.size() - 1;
        int total = high;
        int low = 0;
        //中间值
        int middle = -1;
        //下标索引
        int index = -1;
        //最接近数组长度的但是要比数组长度大的那个斐波那挈数列的下标
        //使用黄金比例查找，f(n-1)个前面的数，f(n-2)个后面的数
        int findex = fibonacciList.size() - 1;
        //数组补齐,存在数组越界的情况，所以需要补齐
        for (int i = high + 1; i <= fibonacciList.get(findex); i++)
            integerList.add(integerList.get(high));
        //开始比较，结束的条件是low大于High
        while (low <= high) {
            //取黄金比例的那个值,数组长度的黄金比例的值，为什么需要-1，不-1的话，
            //直接就取数组最后一位了
            middle = low + fibonacciList.get(findex - 1);
            int middleDataKey = integerList.get(middle);
            //比较元素
            LOG.info("中间值为:" + middleDataKey + "，关键字是:" + keyWord);
            //找到值了就直接返回
            if (middleDataKey == keyWord) {
                //下标落到了补上去的那个数据区间，整个区间的值和最后一个元素相同的
                //直接返回最后一个元素的下标
                if (middle >= total)
                    return total;
                else
                    return middle;
            }
            //如果中间值大于关键字，则在黄金比例的前端，需要修改findex的值了
            if (middleDataKey > keyWord) {
                high = middle - 1;
                findex = findex - 1;
            }
            //如果中间值小于关键字，则在黄金比例的后端，需要修改findex的值了
            else {
                low = middle + 1;
                findex = findex - 2;
            }
        }

        return index;
    }

    //斐波那挈数列的创建
    private List createFibonacci(int lengthofList) {
        //构建数据
        List<Integer> fibonacciList = new ArrayList<Integer>();
        //初始化数据
        fibonacciList.add(0);
        fibonacciList.add(1);
        //标记
        int length = fibonacciList.size() - 1;
        int preOne;
        int preTwo;
        while (lengthofList > fibonacciList.get(length)) {
            preOne = fibonacciList.get(length);
            preTwo = fibonacciList.get(length - 1);
            fibonacciList.add(preOne + preTwo);
            length = fibonacciList.size() - 1;
        }
        //返回序列
        return fibonacciList;
    }

}
