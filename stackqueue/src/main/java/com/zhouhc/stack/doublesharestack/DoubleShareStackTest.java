package com.zhouhc.stack.doublesharestack;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: DoubleShareStackTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/8/28 0:04
 */
public class DoubleShareStackTest {
    private static Logger LOG = LoggerFactory.getLogger(DoubleShareStackTest.class);

    @Test
    public void test(){
        DoubleShareStack<String> dss = new DoubleShareStack<String>();

        for(int i = 0; i<15;i++){
            dss.push(i+"",2);
            LOG.info(dss.getTop(2));
        }
    }
}
