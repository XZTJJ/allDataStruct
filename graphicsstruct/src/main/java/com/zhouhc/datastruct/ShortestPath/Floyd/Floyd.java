package com.zhouhc.datastruct.ShortestPath.Floyd;

import com.zhouhc.datastruct.creategraphics.adjacencymatrix.AdjacencyMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: Floyd
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2020/1/1 12:29
 */

//弗洛伊德算法很牛逼，代码十分的简介
//求的是所有顶点到顶点的最短距离
/*
 *记住这个公式；很牛逼的公式:D表示邻接矩阵
 * D[v][w] = min{D[v][w],D[v][k]+D[k][w]}
 * 对于点vw 而言是直接通过vm直接比较短，还是通过k这一点中转比较短，两者取最小值就可以了
 * 这样就可以求出最小权值，还可以直到路径是通过k中转还是直接连接的
 */

//前提，是无向图；存储:邻接矩阵
//需要使用两个邻接矩阵，一个用于存储最短路径，
//一个用于存储最短距离
public class Floyd {
    private static Logger LOG = LoggerFactory.getLogger(Floyd.class);
    //最小权重数组
    private int[][] miniWight;
    //最短路径算法
    private int[][] miniPart;

    //核心算法,adjacencyMatrix为图的存储结构
    private void shortestPath(AdjacencyMatrix adjacencyMatrix){
        int numbVex = adjacencyMatrix.getNumVertexes();
        //初始化两个数组
        miniWight = new int[numbVex][numbVex];
        miniPart = new int[numbVex][numbVex];
        //miniWight 就初始化图的邻接矩阵形式
        //miniPart  对应的顶点下标，和上面的公式对应，不能初始化为0
        for(int ivex = 0;ivex<numbVex;ivex++) {
            for (int jvex = 0;jvex<numbVex;jvex++){
                miniWight[ivex][jvex] = adjacencyMatrix.getArc()[ivex][jvex];
                miniPart[ivex][jvex] = jvex;
            }
        }

        //开始求距离，神级算法
        //k放在最外层，表示可以通过各个顶点进行中转
        for(int kvex = 0;kvex <numbVex;kvex++){
            for(int ivex = 0;ivex <numbVex;ivex++){
                for(int jvex = 0;jvex<numbVex;jvex++){
                    //利用上面的公司求解,后面两个条件表示对应两点可连通的条件,以及自己到自己节点不考虑
                    if((miniWight[ivex][kvex]+miniWight[kvex][jvex] < miniWight[ivex][jvex]) && (miniWight[ivex][kvex] != Integer.MAX_VALUE) && (miniWight[kvex][jvex] != Integer.MAX_VALUE) && (ivex != jvex)){
                        //权值更新，最短路劲更新
                        miniWight[ivex][jvex] = miniWight[ivex][kvex]+miniWight[kvex][jvex];
                        miniPart[ivex][jvex] = miniPart[ivex][kvex];
                    }
                }
            }
        }
    }

    //求权值和最短路径的方式，通过上面的公私可以求路径是通过列来求的，和缔结斯科拉算法一样，逐一地推
    //statVex开始顶点   endVex 结束顶点   adjacencyMatrix 邻接矩阵
    public void findShortPart(String statVex, String endVex,AdjacencyMatrix adjacencyMatrix){

        //算法核心
        shortestPath(adjacencyMatrix);
        int statIndex = adjacencyMatrix.getVexsIndex().get(statVex);
        int endIndex = adjacencyMatrix.getVexsIndex().get(endVex);
        //临时变量
        int tempIndex = miniPart[statIndex][endIndex];
        int arryaIndex = 0;
        int[] tempArray = new int[endIndex-statIndex];
        //因为pathArc存储的是通过中转的，只需要一层层的找中转就行了,所以列是一直不变的
        tempArray[arryaIndex++]=statIndex;
        tempArray[arryaIndex++]=tempIndex;
        //一直到初始点才算找到了
        while(tempIndex != endIndex){
            tempIndex = miniPart[tempIndex][endIndex];
            tempArray[arryaIndex++]=tempIndex;
        }

        //将下标转成顶点
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0;i<tempArray.length;i++)
            stringBuffer.append(adjacencyMatrix.getVexs()[tempArray[i]]).append("---->");

        stringBuffer.append("权值为:"+miniWight[statIndex][endIndex]);
        LOG.info(stringBuffer.toString());

    }
}
