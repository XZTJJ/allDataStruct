package com.zhouhc.datastruct.creategraphics.adjacencylist;

/**
 * @ClassName: AdjacencyListNode
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/27 8:21
 */

//邻接表的边存储结构，用于存储与当前节点有关系的
//下一个节点索引和权值
public class AdjacencyListEdgeNode {
    //下一个节点的下标
    int adjvex;
    //权值
    int weight;
    //存下一边
    AdjacencyListEdgeNode nextNode;

    //构造函数

    public AdjacencyListEdgeNode(int adjvex) {
        this.adjvex = adjvex;
    }

    public AdjacencyListEdgeNode(int adjvex, int weight, AdjacencyListEdgeNode nextNode) {
        this.adjvex = adjvex;
        this.weight = weight;
        this.nextNode = nextNode;
    }
}
