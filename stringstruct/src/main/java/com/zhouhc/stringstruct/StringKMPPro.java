package com.zhouhc.stringstruct;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @ClassName: StringKMPPro
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/10/8 23:49
 */

//KMp算法的改进版本，主要用于解决不必要的匹配问题
//如  aaaabcde 和 aaaaaX
public class StringKMPPro {

    private static Logger LOG = LoggerFactory.getLogger(StringKMPPro.class);

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

    //next数组的求解方法， 改进版同样也就是修改next数组而已，
    //如果后面位置和前面位置一样，则该相印的next数组就保存上一个位置的数值
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
                if (patternArray[i] == patternArray[j])
                    nextArray[i] = nextArray[j];
                else
                    nextArray[i] = j;
            } else
                j = nextArray[j];
        }

        return nextArray;
    }
}
