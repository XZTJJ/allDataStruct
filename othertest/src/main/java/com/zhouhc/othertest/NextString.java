package com.zhouhc.othertest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName: NextString
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/9/28 22:01
 */
public class NextString {

    private static Logger LOG = LogManager.getLogger(NextString.class);

    @Test
    public void testString(){

        //String s = "ababaaaba";
        //String s = "abcdex";
        //String s = "abcabx";
        //String s = "aaaaaaaab";

        String str="aaaabcdef";
        String[] strArray = str.split("");
        String strsub="ababaaaba";
        String[] strsubArray = strsub.split("");

        Integer[] next = getNextPro(strsub);

        int i = 0,j =0;

        while(i<strArray.length && j<strsubArray.length){

            if(j<0 || strArray[i].equals(strsubArray[j])){
                i++;
                j++;
            }else{
                j = next[j];
            }
        }

        if(j>=strsubArray.length-1)
            LOG.info(i-strsubArray.length);
        else
            LOG.info(-1);
    }


    //getNext数组
    public Integer[] getNext(String s){
        String[] pattern = s.split("");

        Integer[] next = new Integer[pattern.length];

        int i = 0;
        int j = -1;

        next[0] = -1;
        while(i < pattern.length-1){
            if(j < 0 ||  pattern[i].equals(pattern[j]) ){
                i++;
                j++;
                next[i] = j;
            }else{
                j = next[j];
            }

        }

        LOG.info(Arrays.toString(next));
        return next;
    }

    //getNext数组
    public Integer[] getNextPro(String s){
        String[] pattern = s.split("");

        Integer[] next = new Integer[pattern.length];

        int i = 0;
        int j = -1;

        next[0] = -1;
        while(i < pattern.length-1){
            if(j < 0 ||  pattern[i].equals(pattern[j]) ){
                i++;
                j++;
                if(!pattern[i].equals(pattern[j]))
                      next[i] = j;
                else
                    next[i] = next[j];
            }else{
                j = next[j];
            }

        }

        LOG.info(Arrays.toString(next));
        return next;
    }
}
