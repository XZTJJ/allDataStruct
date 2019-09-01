package com.zhouhc.stack.chainStack;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @ClassName: ChainStackTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/9/1 0:06
 */
public class ChainStackTest {

    private static Logger LOG = LogManager.getLogger(ChainStackTest.class);

    @Test
    public void test() {
        ChainStack<String> cs = new ChainStack<String>();

        for (int i = 1; i <= 3; i++) {
            cs.push(i + "");
            LOG.info("length:" + cs.getTop() + " ,top:" + cs.getTop());
        }

        //删除元素
        int size = cs.getSize();
        for (int i = size; i > 0; i--)
            LOG.info("length:" + cs.getSize() + ",top" + cs.pop());
    }
}
