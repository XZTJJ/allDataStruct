package com.zhouhc.datastruct.creategraphics.adjacencymultiplelist;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: AdjacencyMultipleListTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/28 0:30
 */

//对于邻接多重表，一条边只需要输入一次就行了
public class AdjacencyMultipleListTest {
    private static Logger LOG = LoggerFactory.getLogger(AdjacencyMultipleListTest.class);

    @Test
    public void testCrossLinkedList(){
        //顶点字符串
        String vexString = "V0,V1,V2,V3";
        //边串
        String edgesString = "V0#V3,V0#V1,V0#V2,V1#V2,V2#V3";

        AdjacencyMultipleList crossLinlkedList = new AdjacencyMultipleList();
        crossLinlkedList.createAGraphics(vexString,edgesString);
        LOG.info(crossLinlkedList.printGraphics());
    }
}
