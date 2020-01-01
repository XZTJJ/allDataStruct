package com.zhouhc.datastruct.MinimumSpanningTree.Kruskal;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Kruskal
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/30 17:50
 */

//克鲁斯卡尔算法，该算法和Prim算法是两个不同的思想
//克鲁斯卡尔算法是从边出发的，循环所有的边，找到对应小的边
//构成一颗生成树，只要这些边不形成回复就好

/*核心思想，开始时，把各个顶点看成一个个独立的连通分量
 *循环所有的边，对每一个边试着加入到上上面的那些连通分量中
 * 如果加了了这条边，形成了回路，这条边就舍弃，如果没有形成回来
 *就加入。 直到只剩下最后一个连通分量，即该连通分量就是最小生成树
 */
//克鲁斯卡尔需要用到一个新的存储结构，边集数组
//存储结构:邻接矩阵
//前提条件：图要是连通图，即任意两边都是连通的
public class Kruskal {

    private static Logger LOG = LoggerFactory.getLogger(Kruskal.class);

    //连通分量数组
    private int[] isRing;
    //顶点数组
    private String[] vexsArray;
    //边集数组
    private KruskalNode[] kruskalNodes;
    //顶点与下标的对应关系
    private Map<String, Integer> vexsIndex;

    //最小生成树算法
    public void miniSpanningTree(String vexString, String edgesString) {
        //顺序数组
        KruskalNode[] sortEdge = getSortEdge(vexString, edgesString);
        //标记为各个独立连通分量了
        isRing = new int[vexsArray.length];
        for (int i = 0; i < isRing.length; i++)
            isRing[i] = 0;
        //开始进行查找,要遍历所有的边
        for (int i = 0; i < sortEdge.length; i++) {
            //其中的一条边哈
            KruskalNode kruskalNode = sortEdge[i];
            //对一条的两个顶点进行分别查看，看看他们是否在
            //同一个形成一个环
            int m = findNext(kruskalNode.iVex);
            int n = findNext(kruskalNode.jVex);
            //只有nm不相等才不会形成环
            if(m!=n){
                //打印操作
                LOG.info("找到其中的一条边为:<"+vexsArray[kruskalNode.iVex]+","+vexsArray[kruskalNode.jVex]+">，权值为:"+kruskalNode.weight);
                //标记，这两个点属于同一个连通分量
                isRing[m]=n;
            }
        }
    }


    //用于查找是否会形成环
    private int findNext(int vexs) {
        //用于寻找时在那个连通分量之中
        while (isRing[vexs] > 0)
            vexs = isRing[vexs];
        return vexs;
    }

    //创建相关的数据
    private KruskalNode[] getSortEdge(String vexString, String edgesString) {
        //分割字符串
        String[] vexs = StringUtils.splitByWholeSeparatorPreserveAllTokens(vexString, ",");
        String[] edgesArrays = StringUtils.splitByWholeSeparatorPreserveAllTokens(edgesString, ",");
        //初始化
        vexsArray = new String[vexs.length];
        kruskalNodes = new KruskalNode[edgesArrays.length];
        vexsIndex = new HashMap<String, Integer>();
        //顶点和下标的关系
        for (int i = 0; i < vexs.length; i++) {
            vexsArray[i] = vexs[i];
            vexsIndex.put(vexs[i], i);
        }

        //创建边数据
        for (int i = 0; i < edgesArrays.length; i++) {
            String[] params = StringUtils.splitByWholeSeparatorPreserveAllTokens(edgesArrays[i], "#");

            if (params.length < 3)
                throw new RuntimeException("边输入错误");

            int ivex = vexsIndex.get(params[0]);
            int jvex = vexsIndex.get(params[1]);
            int weight = Integer.valueOf(params[2]);
            kruskalNodes[i] = new KruskalNode(ivex, jvex, weight);
        }

        //对数组进行排序
        KruskalNode[] sortKruskalArray = new KruskalNode[edgesArrays.length];
        //返回有序的数列
        MSort(kruskalNodes, sortKruskalArray, 0, kruskalNodes.length - 1);

        return sortKruskalArray;
    }

    //使用归并算法进行数组的排序,一般分为两个步骤，
    //将数组按照2的倍数进行分割成长度只有1许多小数组，
    //对这些小数组进行分割的逆顺序进行排序和合并，
    // 直到只剩下一个数组为止. 合并以后的数组和原来的数据时两个不同的数组
    //testArray 元素组，sortArray合并以后的数组，left数组开始位置，right数组结束位置
    private static void MSort(KruskalNode[] sourceArray, KruskalNode sortArray[], int left, int right) {
        //求中间的值，用于分割两个数组
        int middle = (left + right) / 2;
        //只有当数组元素为1个时，表示是要进行合并了，是合并的数组元素，所以要存到合并数组中去
        if (right - left < 1) {
            //合并操作
            sortArray[middle] = sourceArray[middle];
            return;
        }

        //要把数组分割成之包含两个元素的小数组才行
        MSort(sourceArray, sortArray, left, middle);
        MSort(sourceArray, sortArray, middle + 1, right);
        //合并操作
        Merge(sortArray, left, middle, right);
    }

    //排序以及合并操作
    private static void Merge(KruskalNode sortArray[], int left, int middle, int right) {
        //两个数组分别对应的开始下标
        int leftIndex = left;
        int rightIndex = middle + 1;
        //临时存储排序好的数组
        KruskalNode[] sortTemp = new KruskalNode[right - left + 1];
        //临时存储数据开始索引，开始索引永远都为1
        int sortIndex = 0;
        //对两个数组进行排序，每个元素逐一比较，大的放后面，小的放前面
        while (leftIndex <= middle && rightIndex <= right) {
            if (sortArray[leftIndex].weight < sortArray[rightIndex].weight)
                sortTemp[sortIndex++] = sortArray[leftIndex++];
            else
                sortTemp[sortIndex++] = sortArray[rightIndex++];
        }
        //对两个数组进行检查，看看是否存在着没有加入到排序好的数组中去的元素
        while (leftIndex <= middle)
            sortTemp[sortIndex++] = sortArray[leftIndex++];

        while (rightIndex <= right)
            sortTemp[sortIndex++] = sortArray[rightIndex++];

        //将排序号的数据还原到sortArray中去
        sortIndex = 0;
        while (left <= right)
            sortArray[left++] = sortTemp[sortIndex++];
        //help GC
        sortTemp = null;
    }

}
