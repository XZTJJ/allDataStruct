package com.zhouhc.queue.cyclequeue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @ClassName: CycleQueueTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/9/4 23:22
 */
public class CycleQueueTest {

    private static Logger LOG = LogManager.getLogger(CycleQueueTest.class);

    @Test
    public void test(){
        CycleQueue<String> cq = new CycleQueue<String>();
        LOG.info("length:"+cq.getSize()+", item:"+cq.showAll());

        for(int i =0;i<4;i++) {
            cq.add(i + "");
            LOG.info("length:"+cq.getSize()+", item:"+cq.showAll());
        }

        cq.remove();
        LOG.info("length:"+cq.getSize()+", item:"+cq.showAll());
        cq.remove();
        LOG.info("length:"+cq.getSize()+", item:"+cq.showAll());
        cq.add("4");
        LOG.info("length:"+cq.getSize()+", item:"+cq.showAll());
        cq.add("5");
        LOG.info("length:"+cq.getSize()+", item:"+cq.showAll());
    }

}
