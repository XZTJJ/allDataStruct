package com.zhouhc.datastruct.creategraphics.crosslinkedlist;


import com.zhouhc.datastruct.serialization.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @ClassName: CrossLinlkedList
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/27 22:16
 */
//十字链表使用的存储结构和邻接表有些类似，但又十分的不同
// 十字链表 是对有向图的优化,专门用于有向图的
//十字链表顶点顶点有入度和初读之分，所以除了数据域外，还有一个入度指针，一个出度指针，
// 而且十字链表的边集包含两个顶点弧头，弧尾;两个顶点的指针，一个弧头指针，指向弧头相同的下一条边，一个弧尾指针，指向弧尾相同的下一条边，还有权重
public class CrossLinlkedList {
    private static Logger LOG = LoggerFactory.getLogger(CrossLinlkedList.class);
    //顶点数组
    private CrossLinlkedListNode[] adjList;
    //顶点树，边数
    private int numVertexes, numEdges;
    //用于保存顶点对应的下标数组
    private Map<String, Integer> vexsIndex;

    public CrossLinlkedList() {
    }

    //创建一个图
    //vexString字符串，用,分割，Edges边字符串集合，使用A#D形式
    public void createAGraphics(String vexString, String Edges) {
        //分割字符串
        String[] vexs = StringUtils.splitByWholeSeparatorPreserveAllTokens(vexString, ",");
        String[] edgesArrays = StringUtils.splitByWholeSeparatorPreserveAllTokens(Edges, ",");
        //顶点数，边数
        numVertexes = vexs.length;
        numEdges = edgesArrays.length;
        //初始化顶点数组
        adjList = new CrossLinlkedListNode[vexs.length];
        //初始化顶点和顶点建立对应的下标关系
        for (int i = 0; i < vexs.length; i++) {
            adjList[i] = new CrossLinlkedListNode(vexs[i], null, null);
            vexsIndex = MapUtils.getMapData(vexsIndex, vexs[i], i);
        }
        //填充边的关系，使用的是头插法,firstOut指向弧尾相同的下一个节点，firstIn指向弧尾相同的下一个节点
        for (int i = 0; i < edgesArrays.length; i++) {
            String[] itemItem = StringUtils.splitByWholeSeparatorPreserveAllTokens(edgesArrays[i], "#");
            if (itemItem.length < 2)
                throw new RuntimeException("输入有问题");
            //便顺序一定要相同
            Integer tailVex = vexsIndex.get(itemItem[0]);
            Integer headVex = vexsIndex.get(itemItem[1]);
            //开始填充,初始化，弧头和弧尾,使用头插法，特别好用
            CrossLinlkedListEdgeNode temp = new CrossLinlkedListEdgeNode(tailVex,adjList[tailVex].firstOut,headVex,adjList[headVex].firstIn,0);
            //头插法必须修改头指针的指向
            adjList[tailVex].firstOut = temp;
            adjList[headVex].firstIn = temp;
        }
    }

    //打印整个图
    public String printGraphics() {
        StringBuffer stringBuffer = new StringBuffer();
        //遍历数据
        for (int i = 0; i < numVertexes; i++){
            CrossLinlkedListEdgeNode temp = adjList[i].firstOut;
            //先查出度
            while(temp!= null){
                stringBuffer.append("<").append(adjList[i].data).append(",").append(adjList[temp.headVex].data).append(">").append(",");
                temp = temp.tailLink;
            }
            //然后是入度
            temp = adjList[i].firstIn;
            while(temp!= null){
                stringBuffer.append("<").append(adjList[temp.tailVex].data).append(",").append(adjList[i].data).append(">").append(",");
                temp = temp.headLink;
            }
        }
        //因为查了两边，所有图中的表绝对会输出两次，少一条都不行，只查出度或者入度的话，就只会出现两边了
        return StringUtils.substring(stringBuffer.toString(), 0, stringBuffer.length() - 1);
    }
}
