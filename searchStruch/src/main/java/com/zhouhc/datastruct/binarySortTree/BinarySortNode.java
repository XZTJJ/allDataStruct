package com.zhouhc.datastruct.binarySortTree;

public class BinarySortNode {
    int data;
    BinarySortNode lchild, rchild;

    public BinarySortNode() {
    }

    public BinarySortNode(int data, BinarySortNode lchild, BinarySortNode rchild) {
        this.data = data;
        this.lchild = lchild;
        this.rchild = rchild;
    }
}
