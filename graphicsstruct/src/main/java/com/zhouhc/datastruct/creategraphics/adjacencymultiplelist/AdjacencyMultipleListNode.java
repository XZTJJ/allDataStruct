package com.zhouhc.datastruct.creategraphics.adjacencymultiplelist;

/**
 * @ClassName: AdjacencyMultipleListNode
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/28 0:10
 */

//顶点集，包含一个数据域，一个指针域
public class AdjacencyMultipleListNode {
    //数据域
    String data;
    //指针域
    AdjacencyMultipleListEdgeNode next;

    public AdjacencyMultipleListNode(String data, AdjacencyMultipleListEdgeNode next) {
        this.data = data;
        this.next = next;
    }
}
