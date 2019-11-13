package com.zhouhc.stack.chainStack;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ChainStackTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/9/1 0:06
 */
public class ChainStackTest {

    private static Logger LOG = LoggerFactory.getLogger(ChainStackTest.class);

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
