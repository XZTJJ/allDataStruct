package com.zhouhc.forestBinaryTree;

import com.zhouhc.binarytreeStruct.BinaryTreeNode;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @ClassName: ForestBinaryTreeTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/11/18 17:57
 */

//树，森林和二叉树的相互转换,直接使用String 类型了，不使用泛型，测试
public class ForestBinaryTreeTest {

    private static Logger LOG = LoggerFactory.getLogger(ForestBinaryTreeTest.class);


    @Test
    public void testTree() {
        //树的存储形式，父子键值对形式存在，根节点使用#作为key
        //很复杂的树
        String input = "(#,A),(A,B),(A,C),(A,D),(B,E),(B,F),(B,G),(C,H),(C,I),(C,J),(D,K),(D,L),(D,M),(E,N),(E,O)" +
                ",(E,P),(H,Q),(I,R),(J,S),(K,T),(K,U),(L,V),(M,W),(M,X),(W,Y),(Y,Z)";
        //复杂的树
        //String input = "(#,A),(A,B),(A,C),(A,D),(B,E),(B,F),(B,G),(C,H),(D,I),(D,J)";
        ForestBinaryTree fbt = new ForestBinaryTree();
        BinaryTreeNode binaryTreeNode = fbt.treeToBinaryTree(input);
        Map map = fbt.binaryTreeToTree(binaryTreeNode);

        LOG.info("相互转换完成");
    }

    @Test
    public void testForest() {
        //森林的存储形式，父子键值对形式存在，根节点使用#作为key
        //复杂的树
        String input = "(#,A),(A,B),(A,C),(A,D),(#,E),(E,F),(#,G),(G,H),(G,I),(H,J)";
        ForestBinaryTree fbt = new ForestBinaryTree();
        BinaryTreeNode binaryTreeNode = fbt.forestToBinaryTree(input);
        Map map = fbt.binaryTreeToForest(binaryTreeNode);

        LOG.info("相互转换完成");
    }
}
