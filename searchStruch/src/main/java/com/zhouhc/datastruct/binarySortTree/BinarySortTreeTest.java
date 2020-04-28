package com.zhouhc.datastruct.binarySortTree;


import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class BinarySortTreeTest {
    private static Logger LOG = LoggerFactory.getLogger(BinarySortTreeTest.class);

    @Test
    public void testBinarySortTree() {
        BinarySortTree avlTreePeo = new BinarySortTree();
        //插入数据测试
        List<Integer> intList = new ArrayList<Integer>();
        //用于输出排重后的样例
        List<Integer> reportList = new ArrayList<Integer>();
        //使用随机数来测试一下
        int count = 0;
        Random random = new Random();
        while (count < 20) {
            int i = random.nextInt(100);
            if (!reportList.contains(i)) {
                reportList.add(i);
                count++;
            }
            intList.add(i);
        }

        //转成数组
        LOG.info("测试样例为：[" + StringUtils.join(intList.toArray(), ", ") + "]");
        LOG.info("排重后的样例结果为：[" + StringUtils.join(reportList.toArray(), ", ") + "]");
        //测试插入
        BinarySortNode rootNode = null;
        String[] sArray = null;
        for (int i = 0; i < intList.size(); i++) {
            //插入操作
            rootNode = avlTreePeo.insertNode(rootNode, intList.get(i));
            sArray = avlTreePeo.minddleSort(rootNode);
            LOG.info("插入后" + intList.get(i) + ",整棵树的中序遍历结果为：" + Arrays.toString(sArray));
        }

        //查找测试
        for (int i = 0; i < intList.size(); i++) {
            //插入操作
            BinarySortNode tempNode = avlTreePeo.indexOf(rootNode, intList.get(i));
            LOG.info("要查找的值为:"+tempNode.data+",查找到的值为:" + tempNode.data);
        }
        sArray = avlTreePeo.minddleSort(rootNode);
        LOG.info("整棵树的中序遍历结果为：" + Arrays.toString(sArray));
        //测试删除
        while (intList.size() > 0) {
            int index = random.nextInt(intList.size());
            int data = intList.get(index);
            //刪除操作
            rootNode = avlTreePeo.deleteNode(rootNode, data);
            sArray = avlTreePeo.minddleSort(rootNode);
            LOG.info("删除后" + data + ",整棵树的中序遍历结果为：" + Arrays.toString(sArray));
            intList.remove(index);
        }
    }
}
