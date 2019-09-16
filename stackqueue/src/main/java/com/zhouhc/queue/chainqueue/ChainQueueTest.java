package com.zhouhc.queue.chainqueue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @ClassName: ChainQueueTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/9/16 22:19
 */
public class ChainQueueTest {
    private static Logger LOG = LogManager.getLogger(ChainQueueTest.class);


    @Test
    public void test() {
        ChainQueue<String> sq = new ChainQueue<String>();

        for (int i = 0; i <= 4; i++) {
            sq.add(i + "");
            LOG.info("length:" + sq.getSize() + ", items:" + sq.showAll());
        }

        int size = sq.getSize();
        for (int i = size; i > 0; i++)
            LOG.info("remove item:" + sq.remove() + ", length:" + sq.getSize() + ", items:" + sq.showAll());
    }
}
