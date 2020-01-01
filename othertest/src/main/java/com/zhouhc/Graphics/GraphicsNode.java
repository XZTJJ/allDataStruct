package com.zhouhc.Graphics;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: GraphicsNode
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/12 0:04
 */

//有向图的十字链表存储结构
public class GraphicsNode {

    private static Logger LOG = LoggerFactory.getLogger(GraphicsNode.class);

    private int vertersNumber;
    private int sideNumber;
    private VertexNode[] vertexNodes;

    //构造函数
    public GraphicsNode(int vertersNumber, int sideNumber) {
        this.vertersNumber = vertersNumber;
        this.sideNumber = sideNumber;
        this.vertexNodes = new VertexNode[vertersNumber];
    }

    public void createAGraph(String verters,String side){
        side = StringUtils.substring(side,1,side.length()-1);
        String[] sideArrays = StringUtils.splitByWholeSeparatorPreserveAllTokens(side,">,<");
        String[] vertersArrays = StringUtils.split(verters,",");

        //开始配置xinx
        for(int i = 0;i<vertersNumber;i++)
            vertexNodes[i] = new VertexNode(vertersArrays[i],null,null);

        for(int i = 0;i<sideArrays.length;i++){
            String[] sideItem = StringUtils.splitByWholeSeparatorPreserveAllTokens(sideArrays[i],",");

            int tailIndex = Integer.valueOf(sideItem[0]);
            int headIndex = Integer.valueOf(sideItem[1]);

            sideNode sideNodeTemp = new sideNode(tailIndex,headIndex,null,null);

            sideNodeTemp.taillink = vertexNodes[tailIndex].firstOut;
            vertexNodes[tailIndex].firstOut = sideNodeTemp;

            sideNodeTemp.headlink = vertexNodes[headIndex].firstIn;
            vertexNodes[headIndex].firstIn = sideNodeTemp;
        }

        LOG.info("头插法创建完毕");
    }


    public void createAGraphTail(String verters,String side){
        side = StringUtils.substring(side,1,side.length()-1);
        String[] sideArrays = StringUtils.splitByWholeSeparatorPreserveAllTokens(side,">,<");
        String[] vertersArrays = StringUtils.split(verters,",");

        //开始配置xinx
        for(int i = 0;i<vertersNumber;i++)
            vertexNodes[i] = new VertexNode(vertersArrays[i],null,null);

        for(int i = 0;i<sideArrays.length;i++){
            String[] sideItem = StringUtils.splitByWholeSeparatorPreserveAllTokens(sideArrays[i],",");

            int tailIndex = Integer.valueOf(sideItem[0]);
            int headIndex = Integer.valueOf(sideItem[1]);

            sideNode sideNodeTemp = new sideNode(tailIndex,headIndex,null,null);

            //尾插法
            //出边表
            sideNode iterotarSide = vertexNodes[tailIndex].firstOut;
            while(iterotarSide != null && iterotarSide.taillink != null)
                iterotarSide = iterotarSide.taillink;
            if(iterotarSide == null)
                vertexNodes[tailIndex].firstOut = sideNodeTemp;
            else
                iterotarSide.taillink = sideNodeTemp;
            //入边表
            iterotarSide = vertexNodes[headIndex].firstIn;
            while(iterotarSide != null && iterotarSide.taillink != null)
                iterotarSide = iterotarSide.headlink;
            if(iterotarSide == null)
                vertexNodes[headIndex].firstIn = sideNodeTemp;
            else
                iterotarSide.headlink = sideNodeTemp;

            iterotarSide = null;
        }

        LOG.info("尾插法创建完毕");
    }

    //节点表
    class VertexNode{
        private String data;
        private sideNode firstIn;
        private sideNode firstOut;

        public VertexNode() {
        }

        public VertexNode(String data, sideNode firstIn, sideNode firstOut) {
            this.data = data;
            this.firstIn = firstIn;
            this.firstOut = firstOut;
        }
    }

    //边表
    class sideNode{
        private int tailver;
        private int headver;
        private sideNode headlink;
        private sideNode taillink;

        public sideNode() {
        }

        public sideNode(int tailver, int headver, sideNode headlink, sideNode taillink) {
            this.tailver = tailver;
            this.headver = headver;
            this.headlink = headlink;
            this.taillink = taillink;
        }
    }
}
