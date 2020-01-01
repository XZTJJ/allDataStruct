package com.zhouhc.datastruct.graphicstraversal.BFSTraversal;

import com.zhouhc.datastruct.creategraphics.adjacencymatrix.AdjacencyMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName: BFSTraversal
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/29 18:27
 */

//广度优先遍历，类似与树的层序遍历，
//先输入一个节点，然后遍历遍历与这个节点有关联的
//节点，使用队列进行保存暂时没有遍历的节点
public class BFSTraversal {
    private static Logger LOG = LoggerFactory.getLogger(BFSTraversal.class);
    //标记
    private boolean [] isVisited;
    //队列
    private Queue<Integer> queue;

    public BFSTraversal() {
    }

    //设置信息
    public void Traversal(AdjacencyMatrix adjacencyMatrix){
        if(adjacencyMatrix == null)
            return;
        int vexNum = adjacencyMatrix.getNumVertexes();
        isVisited = new boolean[vexNum];
        //初始化
        for(int i = 0;i<vexNum;i++)
            isVisited[i] = false;
        queue = new LinkedList<Integer>();
        //要进行vexNum的遍历才能保证不会漏掉,输入某个顶点
        BFS(adjacencyMatrix);
    }

    //遍历，类似与树的层序遍历
    private void  BFS(AdjacencyMatrix adjacencyMatrix){
        //获取顶点序列
        String[] vexs = adjacencyMatrix.getVexs();
        //遍历所有的节点，防止漏掉
        for(int index  = 0;index<adjacencyMatrix.getNumVertexes();index++) {
            //入队列,并标记已经访问了
            if (!isVisited[index]) {
                queue.add(index);
                isVisited[index] = true;
                //只有当队列不为空的情况下，就一直循环
                while (queue.size() > 0) {
                    //节点弹出
                    Integer nextIndex = queue.poll();
                    //打印当前序列
                    LOG.info("当前访问的节点位:" + vexs[nextIndex]);
                    //获取临时节点
                    int[] tempVexIndex = adjacencyMatrix.getArc()[nextIndex];
                    //循环
                    for (int i = 0; i < tempVexIndex.length; i++) {
                        //找到节点并且入队列
                        if (!isVisited[i] && tempVexIndex[i] == 1) {
                            queue.add(i);
                            isVisited[i] = true;
                        }
                    }
                }
            }
        }
    }
}
