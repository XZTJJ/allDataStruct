package com.zhouhc.binarytreeStruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: BinaryTreeStruct
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/11/16 17:38
 */

//二叉树的遍历创建，遍历，树高，查找节点，节点的双亲
//二叉树的输入的形式为 "AB#D##C##" ，一个树没有孩子节点，用#标识
public class BinaryTreeStruct<T> {

    private static Logger LOG = LoggerFactory.getLogger(BinaryTreeStruct.class);

    private int index = 0;

    //备份一下根节点
    private BinaryTreeNode<T> rootNode;

    public BinaryTreeStruct() {
    }

    //创建一棵树，使用的是递归的方式，十分的方便
    public BinaryTreeNode createBinaryTree(T[] inputArrays) {

        if (rootNode != null)
            return rootNode;
        if (inputArrays == null || inputArrays.length == 0)
            return null;

        rootNode = recursiveCreateBinaryTree(inputArrays);

        return rootNode;
    }

    //递归创建一棵树
    private BinaryTreeNode recursiveCreateBinaryTree(T[] inputArrays) {
        T tempDate = inputArrays[index];
        index++;
        if (tempDate.equals("#"))
            return null;

        BinaryTreeNode treeNode = new BinaryTreeNode(tempDate);
        treeNode.lchild = recursiveCreateBinaryTree(inputArrays);
        treeNode.rchild = recursiveCreateBinaryTree(inputArrays);

        return treeNode;
    }

    //求树高
    public int getTreeHight(BinaryTreeNode rootTreeNode) {
        if (rootTreeNode == null)
            return 0;
        int hight = recursiveGetTreeHight(rootTreeNode, 0);
        return hight;
    }

    //递归求树高，逐一左右孩子谁高，返回谁
    private int recursiveGetTreeHight(BinaryTreeNode rootTreeNode, int height) {
        if (rootTreeNode == null)
            return height;
        height++;
        int left = recursiveGetTreeHight(rootTreeNode.lchild, height);
        int right = recursiveGetTreeHight(rootTreeNode.rchild, height);

        return left > right ? left : right;
    }

    //查找某个节点
    public BinaryTreeNode findANode(BinaryTreeNode rootTreeNode, T e) {
        //处理树为空的情况
        if (rootTreeNode == null)
            return null;
        BinaryTreeNode current = recursiveFindANode(rootTreeNode, e, null);
        return current;
    }

    //递归查找某个节点
    private BinaryTreeNode recursiveFindANode(BinaryTreeNode rootTreeNode, T e, BinaryTreeNode tempTreeNode) {
        if (rootTreeNode.data.equals(e))
            return rootTreeNode;
        //递归左右子树
        if (rootTreeNode.lchild != null && tempTreeNode == null)
            tempTreeNode = recursiveFindANode(rootTreeNode.lchild, e, tempTreeNode);
        if (rootTreeNode.rchild != null && tempTreeNode == null)
            tempTreeNode = recursiveFindANode(rootTreeNode.rchild, e, tempTreeNode);
        return tempTreeNode;
    }

    //查找某个节点父节点
    public BinaryTreeNode findANodeParent(BinaryTreeNode rootTreeNode, T e) {
        //树为空直接返回
        if (rootTreeNode == null)
            return null;
        //处理输入的节点为根节点
        if (rootTreeNode.data.equals(e))
            return null;
        BinaryTreeNode parentNode = recursiveFindANodeParent(rootTreeNode, e, null);
        return parentNode;
    }

    //递归查找某个节点父节点
    private BinaryTreeNode recursiveFindANodeParent(BinaryTreeNode rootTreeNode, T e, BinaryTreeNode tempTreeNode) {
        if (rootTreeNode.lchild != null && rootTreeNode.lchild.data.equals(e))
            return rootTreeNode;
        if (rootTreeNode.rchild != null && rootTreeNode.rchild.data.equals(e))
            return rootTreeNode;
        //递归左右子树,加上tempTreeNode == null这个条件，是为了防找到数据了，树不用进行不必要的递归，而是直接一层层返回tempTreeNode
        if (rootTreeNode.lchild != null && tempTreeNode == null)
            tempTreeNode = recursiveFindANodeParent(rootTreeNode.lchild, e, tempTreeNode);
        if (rootTreeNode.rchild != null && tempTreeNode == null)
            tempTreeNode = recursiveFindANodeParent(rootTreeNode.rchild, e, tempTreeNode);
        return tempTreeNode;
    }

    //先序遍历,顺序 :  根 左 右
    public void preOrderTraverse(BinaryTreeNode rootTreeNode) {
        if (rootTreeNode == null)
            return;
        LOG.info(rootTreeNode.data.toString());
        preOrderTraverse(rootTreeNode.lchild);
        preOrderTraverse(rootTreeNode.rchild);

    }

    //中序遍历  左 根 右
    public void inOrderTraverse(BinaryTreeNode rootTreeNode) {
        if (rootTreeNode == null)
            return;
        inOrderTraverse(rootTreeNode.lchild);
        LOG.info(rootTreeNode.data.toString());
        inOrderTraverse(rootTreeNode.rchild);
    }

    //后序遍历  左 右 根
    public void postOrderTraverse(BinaryTreeNode rootTreeNode) {
        if (rootTreeNode == null)
            return;
        postOrderTraverse(rootTreeNode.lchild);
        postOrderTraverse(rootTreeNode.rchild);
        LOG.info(rootTreeNode.data.toString());
    }
}
