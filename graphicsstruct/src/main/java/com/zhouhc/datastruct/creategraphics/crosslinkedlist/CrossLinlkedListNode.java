package com.zhouhc.datastruct.creategraphics.crosslinkedlist;

/**
 * @ClassName: CrossLinlkedListNode
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/27 22:19
 */
//顶点集，有数据域，入度和出度指针
public class CrossLinlkedListNode {
    //数据域
    String data;
    //入度
    CrossLinlkedListEdgeNode firstIn;
    //出度
    CrossLinlkedListEdgeNode firstOut;

    public CrossLinlkedListNode(String data, CrossLinlkedListEdgeNode firstIn, CrossLinlkedListEdgeNode firstOut) {
        this.data = data;
        this.firstIn = firstIn;
        this.firstOut = firstOut;
    }
}
