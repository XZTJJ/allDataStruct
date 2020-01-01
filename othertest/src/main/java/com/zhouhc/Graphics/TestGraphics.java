package com.zhouhc.Graphics;


import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName: TestGraphics
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/12 0:04
 */


public class TestGraphics {

    //创建十字链表
    @Test
    public void testGraphics() {
        int vertexNumbers = 4;
        int SideNumbers = 5;
        String vertexString = "v0,v1,v2,v3";
        String sideString = "<0,3>,<1,0>,<2,0>,<1,2>,<2,1>";

        GraphicsNode gn = new GraphicsNode(vertexNumbers, SideNumbers);

        gn.createAGraph(vertexString, sideString);
        gn.createAGraphTail(vertexString, sideString);
    }


    //创建邻接多重表
    @Test
    public void testGraphicsV2() {
        String vertexString = "v0,v1,v2,v3";
        String sideString = "<0,1>,<1,2>,<2,3>,<3,0>,<0,2>";

        GraphicsNodeV2 gn = new GraphicsNodeV2();
        gn.createAGraph(vertexString, sideString);
    }


    //深度优先算法
    public void DFS(int vertexIndex) {
        //和顶点表一样的，访问标志表，标识该顶点是否放翁了，提醒作用
        boolean[] visitNunber = null;
        //顶点表
        String[] vertexArray = null;
        //vertexArrayArray 为图的邻接矩阵，提醒作用
        int[][] vertexArrayArray = null;

        if (visitNunber[vertexIndex] = true)
            return;
        System.out.println(vertexArray[vertexIndex]);
        visitNunber[vertexIndex] = true;

        int[] edgeSide = vertexArrayArray[vertexIndex];

        for (int i = 0; i < edgeSide.length; i++)
            if (edgeSide[i] == 1)
                DFS(edgeSide[i]);
    }


    //广度优先算法
    public void BFS(){
        //和顶点表一样的，访问标志表，标识该顶点是否放翁了，提醒作用
        boolean[] visitNunber = null;        //顶点表
        String[] vertexArray = null;
        //vertexArrayArray 为图的邻接矩阵，提醒作用
        int[][] vertexArrayArray = null;

        //队列结果
        Queue<Integer> queue = new LinkedBlockingQueue<Integer>();

        for(int i =0;i<visitNunber.length;i++)
            visitNunber[i] = false;

        //开始
        for(int i =0;i<vertexArray.length;i++){
            if(!visitNunber[i]) {
                queue.add(i);
                visitNunber[i] = true;
                //队列的循环
                while(queue.size() > 0){
                    int[] edgesItem = vertexArrayArray[i];
                    //i赋值操作,不然会重复取一个特定顶点的信息
                    i = queue.poll();
                    //打印元素
                    System.out.println(vertexArray[i]);
                    //对i顶点进行获取所有的入队列操作
                    for(int j = 0;j<edgesItem.length;j++){
                        if(edgesItem[j]==1 && (!visitNunber[j])) {
                            queue.add(j);
                            visitNunber[j] = true;
                        }
                    }
                }
            }
        }

    }
}
