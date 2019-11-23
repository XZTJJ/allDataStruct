package com.zhouhc.binarytreeStruct;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: BinaryTreeStructTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/11/18 0:33
 */

//测试二叉树
public class BinaryTreeStructTest {

    private static Logger LOG = LoggerFactory.getLogger(BinaryTreeStructTest.class);

    @Test
    public void testTree() {
        //测试数据
//        String input = "AB#D##C##";
//        String input="ABDH#K###E##CFI###G#J##";
        String input = "ABDH##I##EJ###CF##G##";

        BinaryTreeStruct<String> bts = new BinaryTreeStruct<String>();
        //构建一个树
        BinaryTreeNode binaryTree = bts.createBinaryTree(input.split(""));
        //树高
        int treeHight = bts.getTreeHight(binaryTree);
        //某个节点
        BinaryTreeNode a = bts.findANode(binaryTree, "A");
        BinaryTreeNode c = bts.findANode(binaryTree, "C");
        BinaryTreeNode f = bts.findANode(binaryTree, "F");

        //父节点
        BinaryTreeNode a1 = bts.findANodeParent(binaryTree, "A");
        BinaryTreeNode c1 = bts.findANodeParent(binaryTree, "C");
        BinaryTreeNode f1 = bts.findANodeParent(binaryTree, "F");

        //先序遍历
        bts.preOrderTraverse(binaryTree);
        //中序遍历
        bts.inOrderTraverse(binaryTree);
        //后序遍历
        bts.postOrderTraverse(binaryTree);
    }
}
