package com.zhouhc.datastruct.creategraphics.crosslinkedlist;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: CrossLinlkedListTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/27 23:01
 */
public class CrossLinlkedListTest {

    private static Logger LOG = LoggerFactory.getLogger(CrossLinlkedListTest.class);

    @Test
    public void testCrossLinkedList(){
        //顶点字符串
        String vexString = "V0,V1,V2,V3";
        //边串
        String edgesString = "V0#V3,V1#V0,V2#V0,V1#V2,V2#V1";

        CrossLinlkedList crossLinlkedList = new CrossLinlkedList();
        crossLinlkedList.createAGraphics(vexString,edgesString);
        LOG.info(crossLinlkedList.printGraphics());
    }
}
