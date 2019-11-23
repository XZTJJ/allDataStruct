package com.zhouhc.threadedBinaryTree;

import com.zhouhc.binarytreeStruct.BinaryTreeEnum;
import com.zhouhc.binarytreeStruct.BinaryTreeNode;
import com.zhouhc.binarytreeStruct.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @ClassName: ThreadedBinaryTree
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/11/18 16:00
 */

//线索二叉树
public class ThreadedBinaryTree<T> implements Serializable {

    private static Logger LOG = LoggerFactory.getLogger(ThreadedBinaryTree.class);

    private int index = 0;
    //备份一下根节点
    private BinaryTreeNode<T> rootNode;
    //前驱
    private BinaryTreeNode<T> preNode;

    //头节点
    private BinaryTreeNode<T> headRootNode;
    //头节点方法的前驱
    private BinaryTreeNode<T> preHeadNode;

    //创建一棵树
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

    //递归创建中序线索二叉树
    public void inThreaded(BinaryTreeNode rootTreeNode) {
        if (rootTreeNode == null)
            return;

        inThreaded(rootTreeNode.lchild);

        if (rootTreeNode.lchild == null) {
            rootTreeNode.lTag = BinaryTreeEnum.THRED;
            rootTreeNode.lchild = preNode;
        }

        if (preNode != null && preNode.rchild == null) {
            preNode.rchild = rootTreeNode;
            preNode.rTag = BinaryTreeEnum.THRED;
        }
        //保存前驱
        preNode = rootTreeNode;

        //递归右子树
        inThreaded(rootTreeNode.rchild);
    }


    //递归创建中序线索二叉树，带头节点的
    public BinaryTreeNode inThreadedWithHead(BinaryTreeNode rootTreeNode) {
        headRootNode = new BinaryTreeNode();
        headRootNode.lchild = rootTreeNode;
        //递归创建
        recursiverInThreadedWithHead(rootTreeNode);
        //处理最后一个节点和头节点的关系
        preHeadNode.rTag = BinaryTreeEnum.THRED;
        preHeadNode.rchild = headRootNode;
        headRootNode.rTag = BinaryTreeEnum.THRED;
        headRootNode.rchild = preHeadNode;

        return headRootNode;
    }

    //递归创建带头节点的线索二叉树
    private void recursiverInThreadedWithHead(BinaryTreeNode rootTreeNode) {
        if (rootTreeNode == null)
            return;

        recursiverInThreadedWithHead(rootTreeNode.lchild);

        if (rootTreeNode.lchild == null) {
            rootTreeNode.lTag = BinaryTreeEnum.THRED;
            rootTreeNode.lchild = preHeadNode;
            //处理头节点
            if (preHeadNode == null)
                rootTreeNode.lchild = headRootNode;
        }

        if (preHeadNode != null && preHeadNode.rchild == null) {
            preHeadNode.rchild = rootTreeNode;
            preHeadNode.rTag = BinaryTreeEnum.THRED;
        }
        //保存前驱
        preHeadNode = rootTreeNode;

        //递归右子树
        recursiverInThreadedWithHead(rootTreeNode.rchild);
    }

    //中序线索二叉树遍历
    public void inOrderTraverse(BinaryTreeNode rootTreeNode) {
        BinaryTreeNode tempRootNode = rootTreeNode.lchild;
        //根节点为空的情况
        if (tempRootNode == null)
            return;
        //按照后继遍历
        while (tempRootNode != rootTreeNode) {
            //不仅是第一次开始，每次便都必须首先找到线索，根据线索按后继遍历方式去遍历
            while (tempRootNode.lTag == BinaryTreeEnum.LINK)
                tempRootNode = tempRootNode.lchild;
            //访问节点
            LOG.info(tempRootNode.data.toString());
            //根据线索，按后继遍历方式遍历
            while (tempRootNode.rTag == BinaryTreeEnum.THRED && tempRootNode.rchild != rootTreeNode) {
                tempRootNode = tempRootNode.rchild;
                LOG.info(tempRootNode.data.toString());
            }
            //不是线索时，右孩子就是后继
            tempRootNode = tempRootNode.rchild;
        }
    }

}
