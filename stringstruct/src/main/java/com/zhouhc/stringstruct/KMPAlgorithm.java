package com.zhouhc.stringstruct;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @ClassName: KMPAlgorithm
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/25 20:25
 */

//KMP算法，算法相关思路来源算法导论
public class KMPAlgorithm {

    private static Logger LOG = LoggerFactory.getLogger(KMPAlgorithm.class);

    @Test
    public void testString() {
        //模式串
        String pArray = "ABCDABD";
        //文本串
        String tString = "BBC ABCDAB ABCDABCDABDE";
        //前缀函数
        int[] next = getNext(pArray.toCharArray());
        //匹配
        int index = getIndex(pArray.toCharArray(), tString.toCharArray(), next);
        LOG.info("前缀集合为:"+ Arrays.toString(next)+"，下标为:"+index);
    }

    //开始匹配
    private int getIndex(char[] pArray, char[] tArray, int[] next) {
        //初始化变量
        int k = 0;
        int p;
        //查找文本串里面所有的字符，进行逐一比较
        for (p = 0; p < tArray.length; p++) {
            //当k=0表示没有最大前缀，直接进行下一次for循环的匹配
            //其他情况是模式串回溯
            while (k > 0 && tArray[p] != pArray[k]) {
                //因为求出来的是next[]前缀函数，针对的是模式串和文本串匹配成功的部分
                //也就是pArray[k-1]是和匹配成功的最后一位，回退的话是匹配成功部分的
                //最大前缀的下一位,因为只有匹配成功的不算才能求出最大前缀
                k = next[k - 1];
            }
            //相等的话，就比较下一个
            if (tArray[p] == pArray[k])
                k = k + 1;

            //如果已经匹配成功了，返回下标，因为k是长度，不是下标
            //所以需要结果需要加1
            if (k == pArray.length)
                return p - k + 1;
        }
        return -1;
    }

    //求最大前缀的方法
    //对pArray[1...q]求最大的pArray[1...k],使得pArray[1...k]
    //是pArray[1...q]的后缀
    private int[] getNext(char[] pArray) {
        //next[]是最大的前缀，存储的是对应新的偏移量，不是偏移数量，数量为p-k
        int next[] = new int[pArray.length];
        //因为第一个字符串只有ε的前缀，直接赋值为0(有些赋值为-1)
        next[0] = 0;
        //除第一个字符串以外ie，其他都是有前缀的，比较也是从第一个字符开始比较的
        //所以这里直接初始化为0
        int k = 0;
        //对pArray[q]求最大前缀,因为第一个字符不用比较，直接从第二个字符开始
        for (int q = 1; q < next.length; q++) {
            //k > 0是为防止出现无限循环的情况，或者匹配失败情况
            while (k > 0 && pArray[k] != pArray[q]) {
                //next[k]是通过下面的 k+1来赋值的，所以是逐一递增的，
                //k = next[k]表示，回退到上一个字符进行比较
                k = next[k];
            }
            //相等的话就加1
            if (pArray[k] == pArray[q])
                k = k + 1;
            //赋值
            next[q] = k;
        }
        return next;
    }
}
