package com.zhouhc.datastruct.MinimumSpanningTree.Kruskal;

/**
 * @ClassName: KruskalNode
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/30 18:00
 */

//一种新的存储结构，边集数组
public class KruskalNode {
    //一个顶点
    int iVex;
    //另一个顶点
    int jVex;
    //权重
    int weight;

    public KruskalNode() {
    }

    public KruskalNode(int iVex, int jVex, int weight) {
        this.iVex = iVex;
        this.jVex = jVex;
        this.weight = weight;
    }
}
