package com.zhouhc.stringstruct;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @ClassName: StringKMP
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/10/8 23:06
 */

//KMP匹配算法，KMP算法最核心的就是求出nexct[],
//因为有了next数组 ，匹配错误时，主串不用回溯，继续从当前位置匹配
//而模式串也不用回溯到0的位置，回溯位置是由Next[j]的值决定
public class StringKMP {

    private static Logger LOG = LoggerFactory.getLogger(StringKMP.class);

    @Test
    public void testStringKMP() {
        String tText = "ababaaaababaaaababaaaababaaaababaaaba";
        String pText = "ababaaaba";
        int index = matcnKMP(tText, pText);
        LOG.info("索引位置为:" + index);
    }

    //数组匹配
    private int matcnKMP(String tText, String pText) {
        //转成数组
        char[] tArray = tText.toCharArray();
        char[] pArray = pText.toCharArray();

        int[] nextArray = getNext(pText);

        LOG.info("next 数组为:" + Arrays.toString(nextArray));

        int i = 0, j = 0;

        while (i <= (tArray.length - 1) && j <= (pArray.length - 1)) {

            if (tArray[i] == pArray[j]) {
                i++;
                j++;
            } else {
                j = nextArray[j];
            }
        }

        if (j == pArray.length)
            return i - j;
        else
            return -1;
    }

    //next数组的求解方法
    private int[] getNext(String pattern) {
        //next数组同样也是一个自我匹配的过程，
        //遇到不匹配的话，会找到所有 Pk [ p ,以便实现回溯
        //abababaac  abab 与abaa不匹配， 这个时候abab，有一个a的前缀，
        //所以这个时候就会用ab和aa来匹配
        char[] patternArray = pattern.toCharArray();
        int nextArray[] = new int[patternArray.length];

        int i = 0;
        int j = -1;

        nextArray[0] = -1;

        while (i < patternArray.length - 1) {

            if (j < 0 || patternArray[i] == patternArray[j]) {
                i++;
                j++;
                nextArray[i] = j;
            } else
                j = nextArray[j];
        }

        return nextArray;
    }
}
