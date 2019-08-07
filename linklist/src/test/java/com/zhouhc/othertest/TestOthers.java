package com.zhouhc.othertest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: TestOthers
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/8/6 22:56
 */


//动态扩容
public class TestOthers {

    private static Logger LOGGER = LogManager.getLogger(TestOthers.class);


    @Test
    public void test() {
        List<String> stringList = new ArrayList<>();

        stringList.add("测试1");
        stringList.add("测试2");
        stringList.add("测试3");


        try {
            Field sizeF = stringList.getClass().getDeclaredField("size");
            sizeF.setAccessible(true);
            LOGGER.info(sizeF.get(stringList));

            Field elemArray = stringList.getClass().getDeclaredField("elementData");
            elemArray.setAccessible(true);
            LOGGER.info(Arrays.toString((Object[]) elemArray.get(stringList)));
        } catch (Exception e) {
            LOGGER.error("", e);
        }


        LOGGER.info(stringList);
    }


}
