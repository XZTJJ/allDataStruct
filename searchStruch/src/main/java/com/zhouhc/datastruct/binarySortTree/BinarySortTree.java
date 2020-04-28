package com.zhouhc.datastruct.binarySortTree;

import com.zhouhc.datastruct.avltree.AvlNode;
import com.zhouhc.datastruct.binarySearch.InterpolationSearch;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 二叉树排序树，又叫二叉查找树
 * 满足根结点大于左孩子，小于右孩子
 */
public class BinarySortTree {

    private static Logger LOG = LoggerFactory.getLogger(BinarySortTree.class);

    //构造函数
    public BinarySortTree() {
    }

    //插入操作
    public BinarySortNode insertNode(BinarySortNode rootNode, int data) {
        //不存在值就插入
        if (rootNode == null)
            return new BinarySortNode(data, null, null);
        //比较数据
        int parentData = rootNode.data;
        //值存在直接跳过
        if (parentData == data)
            return rootNode;
        //插入是变成左孩子还是右孩子
        if (parentData > data)
            rootNode.lchild = insertNode(rootNode.lchild, data);
        else
            rootNode.rchild = insertNode(rootNode.rchild, data);
        return rootNode;
    }

    //查找
    public BinarySortNode indexOf(BinarySortNode rootNode, int data) {
        if (rootNode == null)
            return null;
        //比较数据
        int parentData = rootNode.data;
        //值存在直接跳过
        if (parentData == data)
            return rootNode;
        //插入是变成左孩子还是右孩子
        if (parentData > data)
            rootNode = indexOf(rootNode.lchild, data);
        else
            rootNode = indexOf(rootNode.rchild, data);

        return rootNode;
    }


    //删除
    public BinarySortNode deleteNode(BinarySortNode rootNode, int data) {
        //用于标记是左子树还是右子树增高了
        boolean isLeft = true;
        if (rootNode == null)
            return null;
        //获取获取对应的数据
        int rootData = rootNode.data;
        //递归决定对应找到对应的数据
        if (rootData > data) {
            rootNode.lchild = deleteNode(rootNode.lchild, data);
            isLeft = true;
        }
        if (rootData < data) {
            rootNode.rchild = deleteNode(rootNode.rchild, data);
            isLeft = false;
        }
        //删除的详细c操作
        if (rootData == data) {
            rootNode = delNodeDetail(rootNode);
            return rootNode;
        }
        //然后才返回结果操作
        return rootNode;
    }

    //删除的详细操作
    private BinarySortNode delNodeDetail(BinarySortNode delNode) {
        //临时变量
        BinarySortNode parentNode = null;
        BinarySortNode nextNode = null;

        //判断是否是叶子结点
        if(delNode.rchild == null && delNode.lchild == null)
            return null;

        boolean isLeft = true;
        //处理不是叶子结点的情况,寻找前驱
        if(delNode.rchild == null){
            isLeft = false;
            parentNode = delNode;
            //左结点的右孩子
            nextNode =  parentNode.lchild;
            //一直右转到底
            while (nextNode.rchild != null) {
                parentNode = nextNode;
                nextNode = nextNode.rchild;
            }
            //数据域相互替换
            delNode.data = nextNode.data;

            if(parentNode == delNode)
                parentNode.lchild = nextNode.lchild;
            else
                parentNode.rchild = nextNode.lchild;
        }
        //寻找后继
        else{
            isLeft = false;
            parentNode = delNode;
            //左结点的右孩子
            nextNode =  parentNode.rchild;
            //一直右转到底
            while (nextNode.lchild != null) {
                parentNode = nextNode;
                nextNode = nextNode.lchild;
            }
            //数据域相互替换
            delNode.data = nextNode.data;

            if(parentNode == delNode)
                parentNode.rchild = nextNode.rchild;
            else
                parentNode.lchild = nextNode.rchild;
        }

        return delNode;
    }


    //中序遍历,递归方法
    private void recursionMinddleSort(BinarySortNode avlNode, List<String> dataList) {
        if (avlNode == null)
            return;
        recursionMinddleSort(avlNode.lchild, dataList);
        dataList.add(avlNode.data + "");
        recursionMinddleSort(avlNode.rchild, dataList);
    }

    //中序遍历,
    public String[] minddleSort(BinarySortNode avlNode) {
        List<String> dataList = new ArrayList();
        recursionMinddleSort(avlNode, dataList);
        String[] sArray = new String[dataList.size()];
        dataList.toArray(sArray);
        return sArray;
    }
}
