package com.zhouhc.datastruct.creategraphics.adjacencylist;

import com.zhouhc.datastruct.serialization.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @ClassName: AdjacencyList
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/27 8:21
 */

//邻接表使用的存储结构为: 顶点使用的数组形式，边使用的链表形式,
//因此右一个顶点结构，一个边存储的结构
// 邻接表适用于有向图和无向图，尤其是图的边比较少的情况下
public class AdjacencyList {
    private static Logger LOG = LoggerFactory.getLogger(AdjacencyList.class);
    //顶点数组
    private AdjacencyListNode[] adjList;

    //顶点树，边数
    private int numVertexes, numEdges;
    //用于保存顶点对应的下标数组
    private Map<String, Integer> vexsIndex;


    //一定需要顶点树
    public AdjacencyList() {

    }

    //创建一个邻接表
    //vexString字符串，用,分割，Edges边字符串集合，使用A#D形式
    public void createAGraphics(String vexString, String Edges) {
        //分割字符串
        String[] vexs = StringUtils.splitByWholeSeparatorPreserveAllTokens(vexString, ",");
        String[] edgesArrays = StringUtils.splitByWholeSeparatorPreserveAllTokens(Edges, ",");
        //顶点数，边数
        numVertexes = vexs.length;
        numEdges = edgesArrays.length;
        //初始化顶点数组
        adjList = new AdjacencyListNode[vexs.length];
        //初始化顶点和顶点建立对应的下标关系
        for (int i = 0; i < vexs.length; i++) {
            adjList[i] = new AdjacencyListNode(vexs[i],null);
            vexsIndex = MapUtils.getMapData(vexsIndex, vexs[i], i);
        }
        //填充边的关系，使用的是头插法
        for (int i = 0; i < edgesArrays.length; i++) {
            String[] itemItem = StringUtils.splitByWholeSeparatorPreserveAllTokens(edgesArrays[i], "#");
            if (itemItem.length < 2)
                throw new RuntimeException("输入有问题");
            //便顺序一定要相同
            Integer ivex = vexsIndex.get(itemItem[0]);
            Integer jvex = vexsIndex.get(itemItem[1]);
            //开始建立关系,使用的是头插法，头插法在图里面特别的好用
            adjList[ivex].firstNode = new AdjacencyListEdgeNode(jvex,0,adjList[ivex].firstNode);
        }
    }

    //打印整个图
    public String printGraphics() {
        StringBuffer stringBuffer = new StringBuffer();
        //遍历数据
        for (int i = 0; i < numVertexes; i++){
            AdjacencyListEdgeNode tempNode = adjList[i].firstNode;
            while(tempNode!=null){
                stringBuffer.append("(").append(adjList[i].data).append(",").append(adjList[tempNode.adjvex].data).append(")").append(",");
                tempNode = tempNode.nextNode;
            }
        }

        return StringUtils.substring(stringBuffer.toString(), 0, stringBuffer.length() - 1);
    }


}
