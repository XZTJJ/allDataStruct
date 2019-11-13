package com.zhouhc.testlink;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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

    private static Logger LOG = LoggerFactory.getLogger(TestOthers.class);


    @Test
    public void test() {
        List<String> stringList = new ArrayList<>();

        stringList.add("测试1");
        stringList.add("测试2");
        stringList.add("测试3");


        try {
            Field sizeF = stringList.getClass().getDeclaredField("size");
            sizeF.setAccessible(true);
            LOG.info(sizeF.get(stringList)+"");

            Field elemArray = stringList.getClass().getDeclaredField("elementData");
            elemArray.setAccessible(true);
            LOG.info(Arrays.toString((Object[]) elemArray.get(stringList)));
        } catch (Exception e) {
            LOG.error("", e);
        }


        LOG.info(stringList+"");
    }


}
