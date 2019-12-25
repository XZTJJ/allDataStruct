package com.zhouhc.stringstruct;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: StringSimple
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/10/8 22:46
 */
//朴素字符串皮匹配算法
public class StringSimple {
    //字符匹配
    private static Logger LOG = LoggerFactory.getLogger(StringSimple.class);

    @Test
    public void testStringSimple() {
        //给定的文本串
        String tText = "goodgooglgooglgooglgooglgooglgooglgooglgooglgoogle";
        String pText = "google";

        int index = match(tText,pText);

        LOG.info("匹配位置为:" + index);

    }

    //朴素比配算法的缺点就是: 每次匹配错误，主串都要回溯到
    // 主串当前下标索引-模式串下标索引+1的位置，而模式串回溯到0的位置
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
