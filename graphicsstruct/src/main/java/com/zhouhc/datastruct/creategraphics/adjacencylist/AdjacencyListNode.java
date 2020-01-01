package com.zhouhc.datastruct.creategraphics.adjacencylist;

/**
 * @ClassName: AdjacencyListNode
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/27 21:26
 */

//这是顶点存储结构，包含数据域和一个指针域
public class AdjacencyListNode {
    //数据域
    String data;
    //指针与
    AdjacencyListEdgeNode firstNode;

    public AdjacencyListNode(String data, AdjacencyListEdgeNode firstNode) {
        this.data = data;
        this.firstNode = firstNode;
    }
}
