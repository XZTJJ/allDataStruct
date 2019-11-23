package com.zhouhc.forestBinaryTree;

import com.zhouhc.binarytreeStruct.BinaryTreeNode;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.tree.Tree;


import java.util.*;

/**
 * @ClassName: ForestBinaryTree
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/11/18 17:36
 */


//树，森林和二叉树的相互转换,直接使用String 类型了，不使用泛型
public class ForestBinaryTree {

    private static Logger LOG = LoggerFactory.getLogger(ForestBinaryTree.class);

    //临时存储一下转换后的map
    private transient Map<String, List<String>> resutlMap;

    //树转成二叉树
    public BinaryTreeNode treeToBinaryTree(String input) {
        //转成map形式
        resutlMap = stringToMap(input);
        BinaryTreeNode<String> rootTreeNode = null;
        try {
            //构建一棵树
            rootTreeNode = recursiveCreateABinaryTree(resutlMap.get("#"), rootTreeNode);
        } catch (Exception e) {
            LOG.error("树转换错误", e);
        } finally {
            resutlMap = null;
        }

        return rootTreeNode;
    }

    //森林转成二叉树
    public BinaryTreeNode forestToBinaryTree(String input) {
        //转成map形式
        resutlMap = stringToMap(input);
        BinaryTreeNode rootTreeNode = null;
        try {
            //构建一棵树
            rootTreeNode = recursiveCreateABinaryTree(resutlMap.get("#"), rootTreeNode);
        } catch (Exception e) {
            LOG.error("森林转换错误", e);
        } finally {
            resutlMap = null;
        }

        return rootTreeNode;
    }

    //String类型转成map
    private Map stringToMap(String input) {
        //转成数组
        String[] resultArray = StringUtils.split(input, "),(");
        Map<String, List> mapList = new HashMap<String, List>();

        for (int i = 0; i < resultArray.length; i += 2) {
            if (!mapList.containsKey(resultArray[i]))
                mapList.put(resultArray[i], new ArrayList());
            mapList.get(resultArray[i]).add(resultArray[i + 1]);
        }
        return mapList;
    }

    //构建一颗树
    private BinaryTreeNode recursiveCreateABinaryTree(List<String> mapList, BinaryTreeNode<String> rootTree) {
        if (CollectionUtils.isEmpty(mapList))
            return null;
        //用于保存上一个兄弟节点
        BinaryTreeNode<String> tempNode = null;
        //遍历父节点下的所有子节点
        for (int i = 0; i < mapList.size(); i++) {
            //数据域
            String child = mapList.get(i);
            //处理长子问题
            if (i < 1) {
                rootTree = new BinaryTreeNode(child);
                //对每个节点都要遍历直到没有子树，同时将子树复制为左子树
                rootTree.lchild = recursiveCreateABinaryTree(resutlMap.get(child), rootTree.lchild);
                //赋值为长子
                tempNode = rootTree;
            } else {
                //创建兄弟节点
                BinaryTreeNode<String> nextNode = new BinaryTreeNode(child);
                //对每个节点都要遍历直到没有子树，同时将子树复制为左子树
                nextNode.lchild = recursiveCreateABinaryTree(resutlMap.get(child), nextNode.lchild);
                //将这个兄弟节点放在上一个兄弟节点的右子树上
                tempNode.rchild = nextNode;
                //始终保持执行下一个兄弟
                tempNode = tempNode.rchild;
            }
        }
        //帮助GC
        tempNode = null;
        return rootTree;
    }

    //二叉树转成普通的树
    public Map binaryTreeToTree(BinaryTreeNode<String> rootTreeNode) {
        resutlMap = null;
        resutlMap = new HashMap<String, List<String>>();
        //因为根节点对应的是#的可以，所以传一个#的节点进去
        recursiveBinaryTreeToTree(rootTreeNode, new BinaryTreeNode<String>("#"));
        return resutlMap;
    }

    //二叉树转成森林
    public Map binaryTreeToForest(BinaryTreeNode rootTreeNode) {
        resutlMap = null;
        resutlMap = new HashMap<String, List<String>>();
        //因为根节点对应的是#的可以，所以传一个#的节点进去
        recursiveBinaryTreeToTree(rootTreeNode, new BinaryTreeNode("#"));
        return resutlMap;
    }

    //递归二叉树转树或者森林
    private void recursiveBinaryTreeToTree(BinaryTreeNode<String> childNode, BinaryTreeNode<String> parentNode) {
        if (childNode == null)
            return;
        //一直递归到没有子树为止
        recursiveBinaryTreeToTree(childNode.lchild, childNode);
        //对递归完的左孩子进行处理,resultMap不一定含有对应父节点，需要判断
        if (!resutlMap.containsKey(parentNode.data))
            resutlMap.put(parentNode.data, new ArrayList<String>());
        resutlMap.get(parentNode.data).add(childNode.data);
        //保存上一个兄弟节点,用来迭代兄弟
        BinaryTreeNode<String> tempNode = childNode.rchild;
        while (tempNode != null) {
            /*
             * 对每个兄弟的每个节点都要递归左右子树，知道没有子树为止，这样就不会遗漏节点了
             * 传入的参数为当前的兄弟节点的左孩子，和当前兄弟节点
             * */
            recursiveBinaryTreeToTree(tempNode.lchild, tempNode);
            //递归完兄弟节点了，将兄弟节点加入到resultMap中，因为长子(左子树)的时候已经判断可以直接添加了
            resutlMap.get(parentNode.data).add(tempNode.data);
            //始终保持指向下后一个兄弟
            tempNode = tempNode.rchild;
        }
    }

}
