package com.zhouhc.datastruct.MinimumSpanningTree.Kruskal;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: KruskalTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2020/1/1 1:43
 */
public class KruskalTest {

    private static Logger LOG = LoggerFactory.getLogger(KruskalTest.class);

    @Test
    public void testPrim(){
        //测试数据,顶点和对应的边
        String vexs = "v0,v1,v2,v3,v4,v5,v6,v7,v8";
        String edges = "v0#v1#10,v0#v5#11,v1#v6#16,v1#v8#12,v1#v2#18," +
                "v2#v8#8,v2#v3#22,v3#v8#21,v3#v6#24,v3#v7#16,v3#v4#20," +
                "v4#v7#7,v4#v5#26,v5#v6#17,v6#v7#19,v8#v3#21";

        //测试
        Kruskal kruskal = new Kruskal();
        kruskal.miniSpanningTree(vexs,edges);

    }
}
