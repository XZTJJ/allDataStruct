package com.zhouhc.datastruct.creategraphics.adjacencymatrix;


import com.zhouhc.datastruct.serialization.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @ClassName: AdjacencyMatrix
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/26 22:51
 */

//邻接矩阵的创建，使用的是一个二维数组的形式
//适用于无向图和有向图
public class AdjacencyMatrix {
    private static Logger LOG = LoggerFactory.getLogger(AdjacencyMatrix.class);
    //顶点数组
    private String[] vexs;
    //边数组
    private int[][] arc;
    //顶点树，边数
    private int numVertexes, numEdges;
    //用于保存顶点对应的下标数组
    private Map<String, Integer> vexsIndex;

    //一定需要顶点树
    public AdjacencyMatrix() {

    }


    //创建一个邻接矩阵，
    //vexString字符串，用,分割，Edges边字符串集合，使用A#D形式
    public void createAGraphics(String vexString, String Edges) {
        //分割字符串
        vexs = StringUtils.splitByWholeSeparatorPreserveAllTokens(vexString, ",");
        String[] edgesArrays = StringUtils.splitByWholeSeparatorPreserveAllTokens(Edges, ",");
        //顶点数，边数
        numVertexes = vexs.length;
        numEdges = edgesArrays.length;
        //类成员的初始化
        arc = new int[numVertexes][numVertexes];
        //遍历顶点，设置顶点对应的下标
        for (int i = 0; i < vexs.length; i++)
            vexsIndex = MapUtils.getMapData(vexsIndex, vexs[i], i);
        //初始化数组
        for (int i = 0; i < numVertexes; i++)
            for (int j = 0; j < numVertexes; j++)
                arc[i][j] = Integer.MAX_VALUE;
        //填充边的关系，初始化数组，有关系使用1表示
        for (int i = 0; i < edgesArrays.length; i++) {
            String[] itemItem = StringUtils.splitByWholeSeparatorPreserveAllTokens(edgesArrays[i], "#");
            if (itemItem.length < 2)
                throw new RuntimeException("输入有问题");
            //便顺序一定要相同
            Integer ivex = vexsIndex.get(itemItem[0]);
            Integer jvex = vexsIndex.get(itemItem[1]);
            //新增一个判断条件，用于记录权值
            if (itemItem.length == 3)
                arc[ivex][jvex] = Integer.valueOf(itemItem[2]);
            else
                arc[ivex][jvex] = 1;
        }
    }

    //打印整个图
    public String printGraphics() {
        StringBuffer stringBuffer = new StringBuffer();
        //遍历数据
        for (int i = 0; i < numVertexes; i++)
            for (int j = 0; j < numVertexes; j++)
                if (arc[i][j] == 1)
                    stringBuffer.append("(").append(vexs[i]).append(",").append(vexs[j]).append("),");

        return StringUtils.substring(stringBuffer.toString(), 0, stringBuffer.length() - 1);
    }

    public String[] getVexs() {
        return vexs;
    }

    public int[][] getArc() {
        return arc;
    }

    public int getNumVertexes() {
        return numVertexes;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public Map<String, Integer> getVexsIndex() {
        return vexsIndex;
    }
}
