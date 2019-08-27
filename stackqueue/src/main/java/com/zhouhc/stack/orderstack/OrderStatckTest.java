package com.zhouhc.stack.orderstack;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @ClassName: OrderStatckTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/8/27 0:04
 */
public class OrderStatckTest {
    private static Logger LOG = LogManager.getLogger(OrderStatckTest.class);

    @Test
    public void test() {
        OrderStack<String> os = new OrderStack<String>();

        for (int i = 1; i <= 15; i++) {
            os.push("第" + i + "个");
            LOG.info("length:" + os.getSize() + " ，top:" + os.getTop());
        }

        int length = os.getSize();
        for (int i = 0; i < length; i++)
            LOG.info("pop:" + os.pop() + " ,length:" + os.getSize());
    }
}
