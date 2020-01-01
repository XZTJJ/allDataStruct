package com.zhouhc.datastruct.MinimumSpanningTree.Prim;

import com.zhouhc.datastruct.creategraphics.adjacencymatrix.AdjacencyMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @ClassName: Prim
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/30 15:49
 */

//Prim算法的核心就是找到相应的
//距离最近的顶点来生成最小生成
//树的，以顶点位核心
//需要3个辅助变量数组，顶点下标一个用于记录最小两点之间的上一个点下标，用于找到对应的边
//权值数组，一个用于记录，相应两点之间最小权值
//最小生成树顶点数组，用于标识此顶点是否加入最小生成树中了
// 本实例使用的邻接矩阵的存储结构
//最小生成树的，前提条件，图要是连通图，即任意两边都是连通的
public class Prim {
    private static Logger LOG = LoggerFactory.getLogger(Prim.class);

    //权值数组，记录最小权值
    private int[] weightArray;
    //顶点数据，用于记录最小权值的一个顶点
    private int[] vexIndex;
    //最小生成树顶点数组
    private boolean[] minTreeVex;

    //开始Prim算法
    public void miniSpanningTree(AdjacencyMatrix adjacencyMatrix){
        //获取所有的顶点
        String[] vexsArray = adjacencyMatrix.getVexs();
        //初始化操作
        weightArray = new int[vexsArray.length];
        vexIndex = new int[vexsArray.length];
        minTreeVex = new boolean[vexsArray.length];
        //初始化顶点数组，默认都是从下标0开始的
        for(int i = 0;i<vexsArray.length;i++) {
            vexIndex[i] = 0;
            minTreeVex[i] = false;
        }
        //对于0这个顶点默认为true
        minTreeVex[0] = true;
        //初始化权重数组，默认与第一个点有关联的权值，因为一般都是从第一个点开始遍历的
        for(int i = 0;i<vexsArray.length;i++)
            weightArray[i] = adjacencyMatrix.getArc()[0][i];

        //最小权重标识
        int minWeight = Integer.MAX_VALUE;
        //用于记录最小权值的的上一个顶点
        int minVex = 0;

        //一定要遍历所有的顶点，才能找出最小生成树,因为第一个顶点的权值已经被初始化为weightArray,所以不需要循环了
        for(int index  = 1;index<vexsArray.length;index++){
            //每次遍历都要对最小的标识重新赋值，防止上一次的结果对这次产生影响
            minWeight = Integer.MAX_VALUE;
            //遍历权值数组，找到没有加入生成树的，最小顶点
            for(int minWeightIndex = 0; minWeightIndex<vexsArray.length;minWeightIndex++){
                //失踪找到没有加入到树里面的最小顶点，minTreeVex的作用防止重复
                if(weightArray[minWeightIndex] < minWeight && !minTreeVex[minWeightIndex]){
                    minWeight = weightArray[minWeightIndex];
                    minVex = minWeightIndex;
                }
            }

            //标识此顶点已经加入最小生成树中了
            minTreeVex[minVex] = true;
            //打印找到的最小边,也就是生成树中的一条边
            LOG.info("找到其中一条边: <"+vexsArray[vexIndex[minVex]]+","+vexsArray[minVex]+">,权值为:"+minWeight);

            //通过找到其中的最小权值，对weightArray数组和vexIndex进行更新，始终保持weightArray的权值是最小的
            //通俗的说，因为本实例使用同取第一个顶点的权值数组的，当找到一条对应权值最小的边时的,
            //要以该边的另一个顶点（也就是刚刚加入最小生成树的那个顶点）最为中间点，查看其余边是否到第一个顶点存在
            //较小的边，如果有就更weightArray数组和vexIndex数组
            int[] weightTemp = adjacencyMatrix.getArc()[minVex];
            //就是上面注解写的，比价对应的权值，如果小话加入到weightArray中，保持weightArray数组有最小边
            // 并且这条边要是没有加入到最小生成树中的
            for(int temindex = 0;temindex<weightTemp.length;temindex++){
                if(!minTreeVex[temindex] && weightTemp[temindex] < weightArray[temindex]){
                    //跟新最小权值数组
                    weightArray[temindex] =  weightTemp[temindex];
                    //同时更新vexIndex数组，以便下一次循环的时候找到最小边时，是通过那一顶点找到的
                    vexIndex[temindex] = minVex;
                }
            }

        }
    }

}
