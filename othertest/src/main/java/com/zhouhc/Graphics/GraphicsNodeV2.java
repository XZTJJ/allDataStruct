package com.zhouhc.Graphics;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: GraphicsNodeV2
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/12/13 22:22
 */

//无向图的邻接多重表
public class GraphicsNodeV2 {
    private static Logger LOG = LoggerFactory.getLogger(GraphicsNodeV2.class);

    private int vertersNumber;
    private int sideNumber;
    private VertexNode[] vertexNodes;

    public GraphicsNodeV2() {
    }

    public void createAGraph(String verters, String side){
        side = StringUtils.substring(side,1,side.length()-1);
        String[] sideArrays = StringUtils.splitByWholeSeparatorPreserveAllTokens(side,">,<");
        String[] vertersArrays = StringUtils.split(verters,",");

        vertersNumber = vertersArrays.length;
        sideNumber = sideArrays.length;
        vertexNodes = new VertexNode[vertersNumber];
        //开始配置xinx
        for(int i = 0;i<vertersNumber;i++)
            vertexNodes[i] = new VertexNode(vertersArrays[i],null);

        for(int i = 0;i<sideArrays.length;i++){
            String[] sideItem = StringUtils.splitByWholeSeparatorPreserveAllTokens(sideArrays[i],",");

            int ivex = Integer.valueOf(sideItem[0]);
            int jvex = Integer.valueOf(sideItem[1]);

            sideNode sideNodeItem = new  sideNode(ivex,null,jvex,null);

            sideNodeItem.ilink = vertexNodes[ivex].firstSide;
            vertexNodes[ivex].firstSide = sideNodeItem;

            sideNodeItem.jlink = vertexNodes[jvex].firstSide;
            vertexNodes[jvex].firstSide = sideNodeItem;
        }
        LOG.info("头插法创建完毕");
    }



    //节点表
    class VertexNode{
        private String data;
        private sideNode firstSide;

        public VertexNode(String data, sideNode firstSide) {
            this.data = data;
            this.firstSide = firstSide;
        }
    }

    //边表
    class sideNode{
        private int ivex;
        private sideNode ilink;
        private int jvex;
        private sideNode jlink;

        public sideNode(int ivex, sideNode ilink, int jvex, sideNode jlink) {
            this.ivex = ivex;
            this.ilink = ilink;
            this.jvex = jvex;
            this.jlink = jlink;
        }
    }
}
