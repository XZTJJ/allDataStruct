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

//线索二叉树，线索二叉树的构建过程: 先创建一个二叉树，遍历该二叉树，
//遍历顺序的不同，线索化也就不同， 本例是中序线索二叉树
//中序线索二叉树的核心是，使用类变量保存上一个访问的节点，本文使用的是preNode
//对终端节点的左孩子放前驱，右孩子放后继
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

    //创建一棵树，和前面的创建二叉树一样，就不再说了，可以去前面了解
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

    //递归创建中序线索二叉树，有详细解释
    public void inThreaded(BinaryTreeNode rootTreeNode) {
        if (rootTreeNode == null)
            return;

        inThreaded(rootTreeNode.lchild);
        //找到终端节点，如果中左孩子为空，采访
        if (rootTreeNode.lchild == null) {
            //初始化线索
            rootTreeNode.lTag = BinaryTreeEnum.THRED;
            //放前驱，第一个是为空的，后面的才会有前驱
            rootTreeNode.lchild = preNode;
        }
        //核心的一些步骤 preNode != null 对第一次的特殊处理，因为
        //这个时候preNode 是空的， 其实这一部已经是到了父亲节点了
        //而这个时候preNode放的是父节点的左孩子，因为中序遍历
        //顺序为 左 根 右
        if (preNode != null && preNode.rchild == null) {
            //将当前节点放到上个节点的右孩子中
            preNode.rchild = rootTreeNode;
            preNode.rTag = BinaryTreeEnum.THRED;
        }

        //保存前驱,灵魂的一步，其实对终端节点放她的后继，是
        //让程序，遍历到她的根节点的时候才放后继，也就是上面的那一步
        //放完了，就赋值为父亲节点，然后放前驱，就是方法最上面的那步
        preNode = rootTreeNode;

        //递归右子树 ，因为终端节点的右孩子又是空，所以不会改变preNode的值
        inThreaded(rootTreeNode.rchild);
    }


    //递归创建中序线索二叉树，带头节点的，
    //头节点的lchild执行根节点，lTag标记为孩子
    //根节点最左孩子的lTag标识线索，同时根节点的最左孩子的lchild执行头节点
    //头节点右孩子标记为线索，执行根节点的最右孩子，根节点的最右孩子的rTag标识线索，
    //根节点的最右孩子rchild执行头节点
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
            //处理头节点 ，只是前驱，为空表示到了最左的孩子了，因此可以将这个
            //孩子的lTag标记为线索，同时这个孩子的左lchild指向头节点
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

    //中序线索二叉树遍历，带头节点的，从头节点开始，找到最左的孩子，从
    //它的后继开始，只有这个后继不是头节点，同时也是线索，就继续循环。
    //如果后继是孩子的话，那么这个孩子就是后继，只不过，要找到这孩子的
    //最左孩子，根据左孩子的线索，继续上面的循环...
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
