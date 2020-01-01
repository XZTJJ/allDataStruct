package com.zhouhc.datastruct.graphicstraversal.DFSTraversal;

import com.zhouhc.datastruct.creategraphics.adjacencymatrix.AdjacencyMatrix;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: DFSTraversalTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/29 18:02
 */
public class DFSTraversalTest {
    private static Logger LOG = LoggerFactory.getLogger(DFSTraversal.class);

    //开始创建
    @Test
    public void testDFSTraversal(){
        //顶点集
        String vexs = "A,B,C,D,E,F,G,H,I";
        //边集
        String edges = "A#B,A#F,B#A,B#G,B#I,B#C,C#B,C#I,C#D,D#C,D#I,D#G,D#H,D#E," +
                "E#D,E#H,E#F,F#E,F#G,F#A,I#B,I#C,I#D,G#B,G#D,G#H,G#F,H#G,H#D,H#E";

        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix();
        adjacencyMatrix.createAGraphics(vexs,edges);

        DFSTraversal dfsTraversal = new DFSTraversal();
        dfsTraversal.Traversal(adjacencyMatrix);
    }
}
