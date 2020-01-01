package com.zhouhc.datastruct.creategraphics.adjacencylist;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: AdjacencyListTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/27 21:51
 */

//邻接表的存储结构
public class AdjacencyListTest {
    private static Logger LOG = LoggerFactory.getLogger(AdjacencyListTest.class);

    @Test
    public void testAdjacencyList(){
        //顶点字符串
        String vexString = "V0,V1,V2,V3";
        //边串
        String edgesString = "V0#V1,V0#V2,V0#V3,V1#V2,V1#V0,V2#V1,V2#V0,V2#V3,V3#V0,V3#V2";
        //对象创建
        AdjacencyList adjacencyList = new AdjacencyList();
        adjacencyList.createAGraphics(vexString,edgesString);
        LOG.info(adjacencyList.printGraphics());
    }
}
