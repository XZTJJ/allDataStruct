package com.zhouhc.stringstruct;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @ClassName: StringSimple
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/10/8 22:46
 */
//朴素字符串皮匹配算法
public class StringSimple {
    //字符匹配
    private static Logger LOG = LogManager.getLogger(StringSimple.class);

    @Test
    public void testStringSimple() {
        //给定的文本串
        String tText = "goodgooglgooglgooglgooglgooglgooglgooglgooglgoogle";
        String pText = "google";

        int index = match(tText,pText);

        LOG.info("匹配位置为:" + index);

    }

    private int match(String tText,String pText){
        //转成数组
        char[] tArray = tText.toCharArray();
        char[] pArray = pText.toCharArray();

        int i = 0, j = 0;
        //匹配文件
        while (i <= (tArray.length-1) && j <= (pArray.length-1)) {
            //匹配和回溯问题
            if (tArray[i] == pArray[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        if(j == pArray.length)
            return i -j;
        else
            return -1;
    }

}
