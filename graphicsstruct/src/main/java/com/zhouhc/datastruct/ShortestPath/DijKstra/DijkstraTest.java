package com.zhouhc.datastruct.ShortestPath.DijKstra;

import com.zhouhc.datastruct.creategraphics.adjacencymatrix.AdjacencyMatrix;
import org.junit.Test;

/**
 * @ClassName: DijkstraTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2020/1/1 11:13
 */
//测试
public class DijkstraTest {

    @Test
    public void testDijkstraTest(){
        String vexsString = "v0,v1,v2,v3,v4,v5,v6,v7,v8";

        String edgeString = "v0#v1#1,v0#v2#5,v1#v3#7,v1#v4#5,v1#v2#3,v2#v4#1,v2#v5#7," +
                "v3#v6#3,v3#v4#2,v4#v6#6,v4#v7#9,v4#v5#3,v5#v7#5,v6#v7#2,v6#v8#7,v7#v8#4," +
                "v1#v0#1,v2#v0#5,v3#v1#7,v4#v1#5,v2#v1#3,v4#v2#1,v5#v2#7,v6#v3#3,v4#v3#2,v6#v4#6,v7#v4#9,v5#v4#3,v7#v5#5,v7#v6#2,v8#v6#7,v8#v7#4";

        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix();
        adjacencyMatrix.createAGraphics(vexsString,edgeString);


        Dijkstra dijkstra = new Dijkstra();
        dijkstra.findShortPart("v0","v8",adjacencyMatrix);
    }
}
