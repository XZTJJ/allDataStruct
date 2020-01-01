package com.zhouhc.datastruct.creategraphics.adjacencymultiplelist;

import com.zhouhc.datastruct.serialization.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @ClassName: AdjacencyMultipleList
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/28 0:06
 */
//邻接多重表，主要用于无向图的优化，节约空间，邻接多重表的优化思想和十字链表一样
//顶点集，包含一个数据域，一个边的指针域，指向一个顶点相同的下一边
//边表集，包含两个顶点，两个指针，两个指针分别指向顶点相同的下一条表
public class AdjacencyMultipleList {
    private static Logger LOG = LoggerFactory.getLogger(AdjacencyMultipleList.class);
    //顶点数组
    private AdjacencyMultipleListNode[] adjList;
    //顶点树，边数
    private int numVertexes, numEdges;
    //用于保存顶点对应的下标数组
    private Map<String, Integer> vexsIndex;

    public AdjacencyMultipleList() {
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
        adjList = new AdjacencyMultipleListNode[vexs.length];
        //初始化顶点和顶点建立对应的下标关系
        for (int i = 0; i < vexs.length; i++) {
            adjList[i] = new AdjacencyMultipleListNode(vexs[i], null);
            vexsIndex = MapUtils.getMapData(vexsIndex, vexs[i], i);
        }
        //填充边的关系，使用的是头插法,next指针指向下一条边，和十字链表有些类似
        for (int i = 0; i < edgesArrays.length; i++) {
            String[] itemItem = StringUtils.splitByWholeSeparatorPreserveAllTokens(edgesArrays[i], "#");
            if (itemItem.length < 2)
                throw new RuntimeException("输入有问题");
            //便顺序一定要相同
            Integer iVex = vexsIndex.get(itemItem[0]);
            Integer jVex = vexsIndex.get(itemItem[1]);
            //开始填充,初始化，两个顶点,使用头插法，特别好用
            AdjacencyMultipleListEdgeNode temp = new AdjacencyMultipleListEdgeNode(iVex, adjList[iVex].next, jVex, adjList[jVex].next, 0);
            //头插法必须修改头指针的指向
            adjList[iVex].next = temp;
            adjList[jVex].next = temp;
        }
    }

    //打印整个图
    public String printGraphics() {
        StringBuffer stringBuffer = new StringBuffer();
        //遍历数据
        for (int i = 0; i < numVertexes; i++) {
            AdjacencyMultipleListEdgeNode temp = adjList[i].next;
            //先查一条边
            while (temp != null) {
                //这么取的原因是，无向图没有头和尾之分，所以取得时候的判断才行，不然容易出错
                if (temp.iVex == i) {
                    stringBuffer.append("(").append(adjList[i].data).append(",").append(adjList[temp.jVex].data).append(")").append(",");
                    temp = temp.iLink;
                }else{
                    stringBuffer.append("(").append(adjList[i].data).append(",").append(adjList[temp.iVex].data).append(")").append(",");
                    temp = temp.jLink;
                }
            }
        }
        //因为查了两边，所有图中的表绝对会输出两次，少一条都不行,不排重的话，解决不了边重复的问题
        return StringUtils.substring(stringBuffer.toString(), 0, stringBuffer.length() - 1);
    }
}
