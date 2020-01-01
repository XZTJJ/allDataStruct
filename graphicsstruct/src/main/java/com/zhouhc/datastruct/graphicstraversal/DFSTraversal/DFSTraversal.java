package com.zhouhc.datastruct.graphicstraversal.DFSTraversal;

import com.zhouhc.datastruct.creategraphics.adjacencymatrix.AdjacencyMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: DFSTraversal
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/29 17:38
 */


//图的深度遍历，使用的邻接矩阵的存储形式
//图的深度遍历，主要是对图的进行VexNUm(顶点数)次遍历
//每一次的遍历，如果一个顶点有位访问的顶点，一定要
//访问完这个顶点的所有邻接顶点才去访问下一个顶点
public class DFSTraversal {
    private static Logger LOG = LoggerFactory.getLogger(DFSTraversal.class);

    //用于标识是否已经遍历了
    private boolean [] isVisited;

    public DFSTraversal() {
    }

    public void Traversal(AdjacencyMatrix adjacencyMatrix){
        if(adjacencyMatrix == null)
            return;
        int vexNum = adjacencyMatrix.getNumVertexes();
        isVisited = new boolean[vexNum];
        //初始化
        for(int i = 0;i<vexNum;i++)
            isVisited[i] = false;
        //要进行vexNum的遍历才能保证不会漏掉
        for(int i = 0;i<vexNum;i++)
            if(!isVisited[i])
                DFS(adjacencyMatrix,i);
    }
    // adjacencyMatrix 图对象用于遍历
    //index用于标识遍历到那个顶点,
    //一定要访问完这个顶点的所有邻接顶点才去访问下一个顶点
    private void  DFS(AdjacencyMatrix adjacencyMatrix,int index){
        //打印顶点
        String[] vexs = adjacencyMatrix.getVexs();
        LOG.info("访问的顶点是:"+vexs[index]);
        //对访问的过的顶点进行标识
        isVisited[index] = true;
        //获取这个顶点的所有邻接点
        int[] arcs = adjacencyMatrix.getArc()[index];
        //遍历这些邻接点
        for(int i = 0;i<arcs.length;i++)
            //只有相连的并且没有访问的顶点才访问
            if(!isVisited[i] && arcs[i] == 1)
                DFS(adjacencyMatrix,i);

    }

}
