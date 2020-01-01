package com.zhouhc.datastruct.creategraphics.adjacencymultiplelist;

/**
 * @ClassName: AdjacencyMultipleListEdgeNode
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/28 0:11
 */
//包含两个顶点，两个指针域，分别指向顶点相同的下一条边
public class AdjacencyMultipleListEdgeNode {
    //一个顶点
    int iVex;
    //顶点指针
    AdjacencyMultipleListEdgeNode iLink;
    //一个顶点
    int jVex;
    //顶点指针
    AdjacencyMultipleListEdgeNode jLink;
    //权重
    int weight;

    //构造函数
    public AdjacencyMultipleListEdgeNode(int iVex, AdjacencyMultipleListEdgeNode iLink, int jVex, AdjacencyMultipleListEdgeNode jLink, int weight) {
        this.iVex = iVex;
        this.iLink = iLink;
        this.jVex = jVex;
        this.jLink = jLink;
        this.weight = weight;
    }
}
