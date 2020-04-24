package com.zhouhc.datastruct.avltree;

//avl树的结点
public class AvlNode {
    //数据域
    int data;
    //左右孩子
    AvlNode lchild, rchild;
    //平衡因子
    AVLEnum bf;
    //构造函数
    public AvlNode(int data, AvlNode lchild, AvlNode rchild, AVLEnum bf) {
        this.data = data;
        this.lchild = lchild;
        this.rchild = rchild;
        this.bf = bf;
    }
}
