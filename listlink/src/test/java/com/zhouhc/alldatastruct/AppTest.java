package com.zhouhc.alldatastruct;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    private static Logger LOGGER = LoggerFactory.getLogger(AppTest.class);
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test() {
        List<String> stringList = new ArrayList<>();

        stringList.add("测试1");
        stringList.add("测试2");
        stringList.add("测试3");


        try {
            Field sizeF = stringList.getClass().getDeclaredField("size");
            sizeF.setAccessible(true);
            LOGGER.info(sizeF.get(stringList).toString());

            Field elemArray = stringList.getClass().getDeclaredField("elementData");
            elemArray.setAccessible(true);
            LOGGER.info(Arrays.toString((Object[]) elemArray.get(stringList)));
        } catch (Exception e) {
            LOGGER.error("", e);
        }


        LOGGER.info(stringList.toString());
    }
}
