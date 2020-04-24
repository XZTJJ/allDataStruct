package com.zhouhc.datastruct.avltree;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


//可以结合可视化的网站开验证是否正确:
//http://www.u396.com/wp-content/collection/data-structure-visualizations/AVLTree.html
public class AvlTreeTest {
    private static Logger LOG = LoggerFactory.getLogger(AvlTreeTest.class);

    //修改后的版本
    @Test
    public void testAvlTreePro() {
        AvlTreePro avlTreePeo = new AvlTreePro();
        //插入数据测试
        List<Integer> intList = new ArrayList<Integer>();
        /*大话数据结构上面的测试样例
        Integer[] insertData = new Integer[]{3,2,1,4,5,6,7,10,9,8} ;
        intList = new ArrayList<Integer>(Arrays.asList(insertData));
         */
        //使用随机数来测试一下
        int count = 0;
        Random random = new Random();
        while (count < 20) {
            int i = random.nextInt(100);
            if (!intList.contains(i))
                count++;
            intList.add(i);
        }

        //转成数组
        LOG.info("测试样例为：[" + StringUtils.join(intList.toArray(),", ")+ "]");
        //用于输出排重后的样例
        List<Integer> reportList = new ArrayList<Integer>();
        for(int i = 0; i < intList.size(); i++)
            if (!reportList.contains(intList.get(i)))
                reportList.add(intList.get(i));
        LOG.info("排重后的样例结果为：[" + StringUtils.join(reportList.toArray(), ", ") + "]");
        //测试插入
        AvlNode rootNode = null;
        for (int i = 0; i < intList.size(); i++) {
            //插入操作
            rootNode = avlTreePeo.insertNode(rootNode, intList.get(i));
            String[] sArray = avlTreePeo.minddleSort(rootNode);
            LOG.info("插入后"+intList.get(i)+",整棵树的中序遍历结果为：" + Arrays.toString(sArray));
        }

        //测试删除
        while(reportList.size() > 0){
            int index = random.nextInt(reportList.size());
            int  data = reportList.get(index);
            //刪除操作
            rootNode = avlTreePeo.delAvlNode(rootNode,data);
            String[] sArray = avlTreePeo.minddleSort(rootNode);
            LOG.info("删除后"+data+",整棵树的中序遍历结果为：" + Arrays.toString(sArray));
            reportList.remove(index);
        }

    }
}
