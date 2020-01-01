package com.zhouhc.datastruct.ShortestPath.DijKstra;

import com.zhouhc.datastruct.creategraphics.adjacencymatrix.AdjacencyMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @ClassName: Dijkstra
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2020/1/1 9:45
 */

//Dijkstra算法: 核心也是从一个顶点出发
//找到离他最近的顶点，以这个最短的路径为基础
//寻找下一条最短的边。

//需要一个权值数组和一个最短路径下标数组和
// 一个标记标记是否加入到最短路径的数组
//前提时:连通图;存储:邻接矩阵的存储结构
public class Dijkstra {
    private static Logger LOG = LoggerFactory.getLogger(Dijkstra.class);

    //存储权重的下标数组
    private int[] miniWeight;
    //存储最短路径的下标，基于上个顶点找上个顶点
    private int[] pathArc;
    //用于标记是否已经加入到最短路径里去了
    private boolean[] isInShort;

    //以邻接表的形式存储,  vexs给定点的出是顶点,算法核心
    private void shortestPath(AdjacencyMatrix adjacencyMatrix, String vexs) {
        //找到顶点对应的下标
        int indexofVex = adjacencyMatrix.getVexsIndex().get(vexs);
        //初始化相关数据
        miniWeight = new int[adjacencyMatrix.getNumVertexes()];
        pathArc = new int[adjacencyMatrix.getNumVertexes()];
        isInShort = new boolean[adjacencyMatrix.getNumVertexes()];
        //miniWeight初始化为给定下标对应的权值
        for (int i = 0; i < adjacencyMatrix.getNumVertexes(); i++) {
            miniWeight[i] = adjacencyMatrix.getArc()[indexofVex][i];
            pathArc[i] = 0;
            isInShort[i] = false;
        }
        //因为这个顶点始终时找到的啦
        isInShort[indexofVex] = true;
        //用于记录最短的路径
        int min;
        //用于记录最短边的下标数组
        int miniVex;
        //遍历所有的顶点除了，下标为vexs的
        for (int vexIndex = 1; vexIndex < adjacencyMatrix.getNumVertexes(); vexIndex++) {
            //每次循环都初始化，避免影响后面的结果
            min = Integer.MAX_VALUE;
            miniVex = indexofVex;
            //找出最短路径
            for (int miniIndex = 0; miniIndex < miniWeight.length; miniIndex++) {
                //记录没有加入最短路劲里面下一条权值最小的边和他的下标
                if (!isInShort[miniIndex] && miniWeight[miniIndex] < min) {
                    min = miniWeight[miniIndex];
                    miniVex = miniIndex;
                }
            }

            //标记该边已经加入最短路径里面去了
            isInShort[miniVex] = true;
            //最短点的权值数组
            int[] tempWeight = adjacencyMatrix.getArc()[miniVex];
            //通过已经找到的最短路径，看看有没有通过这个点中转的，存在这更短的距离
            for(int miniIndex = 0;miniIndex<tempWeight.length;miniIndex++){
                //如果这条边还没有加入到对应最小生成路劲中去，存在一条对应的路径(从目前最短顶点转到初始点)比其他其他都初始点的要短
                //则更新权值为: 到最短点的权值+最短点权值到初始点的权值,同时最短路径的下标也要跟新为最短点的
                //tempWeight[miniIndex] != Integer.MAX_VALUE 表示无穷大，不要判断了
                if(!isInShort[miniIndex] &&(tempWeight[miniIndex] != Integer.MAX_VALUE) &&(tempWeight[miniIndex]+min<miniWeight[miniIndex])){
                    miniWeight[miniIndex] = tempWeight[miniIndex]+min;
                    pathArc[miniIndex] = miniVex;
                }
            }

        }
    }

    //最短路径，是通过从目标点开始，找到离他最近的点，然后逐一找到离他更近的点的，重复这个过程直到
    //找到初始点为止
    //statIndex初始点    endIndex目标点    adjacencyMatrix图的存储结构
    public void findShortPart(String statVex, String endVex,AdjacencyMatrix adjacencyMatrix){

        //算法核心
        shortestPath(adjacencyMatrix,statVex);
        int statIndex = adjacencyMatrix.getVexsIndex().get(statVex);
        int endIndex = adjacencyMatrix.getVexsIndex().get(endVex);
        //临时变量
        int tempIndex = pathArc[endIndex];
        int arryaIndex = endIndex-1;
        int[] tempArray = new int[endIndex-statIndex];
        //因为pathArc存储的是逆序，不符合人的习惯，所以改成正序
        tempArray[arryaIndex--]=endIndex;
        tempArray[arryaIndex--]=tempIndex;
        //一直到初始点才算找到了
        while(tempIndex != statIndex){
            tempIndex = pathArc[tempIndex];
            tempArray[arryaIndex--]=tempIndex;
        }

        //将下标转成顶点
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0;i<tempArray.length;i++)
            stringBuffer.append(adjacencyMatrix.getVexs()[tempArray[i]]).append("---->");

        stringBuffer.append("权值为:"+miniWeight[endIndex]);
        LOG.info(stringBuffer.toString());

    }
}
