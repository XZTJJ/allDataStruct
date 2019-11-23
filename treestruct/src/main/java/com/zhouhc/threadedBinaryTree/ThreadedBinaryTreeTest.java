package com.zhouhc.threadedBinaryTree;

import com.zhouhc.binarytreeStruct.BinaryTreeNode;
import com.zhouhc.binarytreeStruct.SerializationUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ThreadedBinaryTreeTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/11/18 16:24
 */


//线索二叉树的测试
public class ThreadedBinaryTreeTest {
    private static Logger LOG = LoggerFactory.getLogger(ThreadedBinaryTreeTest.class);

    @Test
    public void testTree() {
        String input = "ABDH##I##EJ###CF##G##";

        ThreadedBinaryTree<String> tbt = new ThreadedBinaryTree<String>();
        BinaryTreeNode binaryTree = tbt.createBinaryTree(input.split(""));

        //序列化一下
        byte[] bytes = SerializationUtils.objToByte(binaryTree);
        BinaryTreeNode binaryTreeBak = (BinaryTreeNode<String>) SerializationUtils.byteToObject(bytes);

        //构建中序线索二叉树
        tbt.inThreaded(binaryTreeBak);
        LOG.info("测试完成");

        //序列化一下
        BinaryTreeNode binaryTreeHead = (BinaryTreeNode<String>) SerializationUtils.byteToObject(bytes);
        //构建中序线索二叉树,带头节点的
        BinaryTreeNode binaryTreeNode = tbt.inThreadedWithHead(binaryTreeHead);
        LOG.info("测试完成,带头节点的");
        //遍历,带头节点的
        tbt.inOrderTraverse(binaryTreeNode);

    }
}
