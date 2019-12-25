package com.zhouhc.binarytreeStruct;

import java.io.Serializable;

/**
 * @ClassName: BinaryTreeNode
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/11/18 0:35
 */

//树节点，data数据与.lchild和rchild是左右孩子，lTag和rTag是标识，标识是孩子还是线索
//一般除了线索二叉树外，lTag和rTag是不需要的
public class BinaryTreeNode<T> implements Serializable {
    public T data;
    public BinaryTreeNode lchild;
    //二叉树时表示有孩子，普通树时表示的是左孩子的兄弟
    public BinaryTreeNode rchild;
    public BinaryTreeEnum lTag;
    public BinaryTreeEnum rTag;

    public BinaryTreeNode() {
        lTag = rTag = BinaryTreeEnum.LINK;
    }

    public BinaryTreeNode(T data) {
        this.data = data;
        lTag = rTag = BinaryTreeEnum.LINK;
    }
}
