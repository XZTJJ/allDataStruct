package com.zhouhc.datastruct.creategraphics.crosslinkedlist;

/**
 * @ClassName: CrossLinlkedListEdgeNode
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/27 22:20
 */
//
public class CrossLinlkedListEdgeNode {
    //弧尾
    int tailVex;
    //弧尾指针，只想弧尾相同的边
    CrossLinlkedListEdgeNode tailLink;
    //弧头
    int headVex;
    //弧尾指针，只想弧尾相同的边
    CrossLinlkedListEdgeNode headLink;
    //权值
    int weight;

    public CrossLinlkedListEdgeNode(int tailVex, CrossLinlkedListEdgeNode tailLink, int headVex, CrossLinlkedListEdgeNode headLink, int weight) {
        this.tailVex = tailVex;
        this.tailLink = tailLink;
        this.headVex = headVex;
        this.headLink = headLink;
        this.weight = weight;
    }
}
