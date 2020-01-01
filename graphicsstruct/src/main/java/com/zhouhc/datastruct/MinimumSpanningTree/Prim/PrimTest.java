package com.zhouhc.datastruct.MinimumSpanningTree.Prim;

import com.zhouhc.datastruct.creategraphics.adjacencymatrix.AdjacencyMatrix;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: PrimTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/30 17:09
 */

//Pine算法测试
public class PrimTest {
    private static Logger LOG = LoggerFactory.getLogger(PrimTest.class);

    @Test
    public void testPrim(){
        //测试数据,顶点和对应的边
        String vexs = "v0,v1,v2,v3,v4,v5,v6,v7,v8";
        String edges = "v0#v1#10,v0#v5#11,v1#v0#10,v1#v6#16,v1#v8#12,v1#v2#18," +
                "v2#v1#18,v2#v8#8,v2#v3#22,v3#v2#22,v3#v8#21,v3#v6#24,v3#v7#16,v3#v4#20," +
                "v4#v3#20,v4#v7#7,v4#v5#26,v5#v4#26,v5#v6#17,v5#v0#11," +
                "v6#v1#16,v6#v3#24,v6#v7#19,v6#v5#17,v7#v6#19,v7#v3#16," +
                "v7#v4#7,v8#v1#12,v8#v2#8,v8#v3#21";
        //数据
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix();
        adjacencyMatrix.createAGraphics(vexs,edges);
        //测试
        Prim prim = new Prim();
        prim.miniSpanningTree(adjacencyMatrix);
    }

}
