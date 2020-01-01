package com.zhouhc.datastruct.creategraphics.adjacencymatrix;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.util.locale.provider.LocaleServiceProviderPool;

/**
 * @ClassName: AdjacencyMatrixTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/27 0:18
 */

//测试邻接矩阵 顶点之间,分割，边与边使用的是(A,S),(D,V)形式的字符串
//无向图 边集要输入两次
public class AdjacencyMatrixTest {
    private static Logger LOG = LoggerFactory.getLogger(AdjacencyMatrixTest.class);

    @Test
    public void testGraphics(){
        //顶点字符串
        String vexString = "V0,V1,V2,V3";
        //边串
        String edgesString = "V0#V1,V0#V2,V0#V3,V1#V2,V1#V0,V2#V1,V2#V0,V2#V3,V3#V0,V3#V2";

        //测试对象
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix();
        adjacencyMatrix.createAGraphics(vexString,edgesString);
        //输出
        LOG.info(adjacencyMatrix.printGraphics());
    }
}
