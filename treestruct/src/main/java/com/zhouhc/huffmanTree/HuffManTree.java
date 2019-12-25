package com.zhouhc.huffmanTree;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: HuffManTree
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/11/20 8:02
 */
public class HuffManTree {
    //日志类型
    private static Logger LOG = LoggerFactory.getLogger(HuffManTree.class);

    //构建一颗哈夫曼树
    public HuffManNode<String> createAHuffManTree(String input) {
        StringBuffer stringBuffer = new StringBuffer();
        //统计,同时构建了节点
        Map<String, HuffManNode<String>> wordCount = getWordCount(input);
        //打印统计结果
        stringBuffer.append("字符出现评论统计: ");
        for (Map.Entry<String, HuffManNode<String>> tempMap : wordCount.entrySet())
            stringBuffer.append(tempMap.getKey()).append("---->").append(tempMap.getValue().count).append("  ");
            LOG.info(stringBuffer.toString());
        stringBuffer.setLength(0);
        List<HuffManNode<String>> listNode = new ArrayList<HuffManNode<String>>();
        //对节点按照频率从小到大的顺序排序以及排序完成
        for (Map.Entry<String, HuffManNode<String>> tempMap : wordCount.entrySet())
            sortListNode(listNode, tempMap.getValue());
        //打印完成的排序
        for (int i = 0; i < listNode.size(); i++)
            stringBuffer.append(listNode.get(i).count).append(" ");
        LOG.info("排序结果:" + stringBuffer.toString());
        //开始构建一个哈夫曼树
        HuffManNode<String> stringHuffManNode = toCreateAHuffManTree(listNode);
        //构建完成
        return stringHuffManNode;
    }

    //开始进行哈夫曼编码工作
    public Map<String, String> codeOfHuffMan(HuffManNode<String> stringHuffManNode) {
        Map<String, String> wordCode = new HashMap<String, String>();
        //参数传入
        recurisveFindHuffManCode(stringHuffManNode, wordCode, "");
        return wordCode;
    }
    //开始进行哈夫曼编码解码工作
    public String unCodeOfHuffMan(String input,HuffManNode<String> stringHuffManNode) {
        //开始解码
        String[] inputArrays = input.split("");
        //下标，临时遍历变量
        int index = 0;
        StringBuffer stringBuffer = new StringBuffer();
        HuffManNode<String> tempNode = stringHuffManNode;
        //循环
        while(index < inputArrays.length){
            //每次都重新赋值为根节点，便于解码
            tempNode = stringHuffManNode;
            //结束标志
            while(true){
                //找到对应的数据,0代表左孩子，1为右孩子
                if(StringUtils.equals(inputArrays[index],"0"))
                    tempNode = tempNode.lchild;
                else
                    tempNode = tempNode.rchild;
                //循环结束标识,数据域不为空，就找到
                 if(StringUtils.isNotEmpty(tempNode.data))
                     break;
                 index++;
            }
            stringBuffer.append(tempNode.data);
            index++;
        }
        return stringBuffer.toString();
    }

    //用于统计单词出现的频率，形式为 A ---> A对应的哈夫曼节点
    //频率存储在节点中count中
    private Map<String, HuffManNode<String>> getWordCount(String input) {
        //返回结果
        Map<String, HuffManNode<String>> resultMap = new HashMap<String, HuffManNode<String>>();
        //转成字母
        String[] inputArrays = input.split("");
        //遍历
        for (int i = 0; i < inputArrays.length; i++) {
            if (!resultMap.containsKey(inputArrays[i]))
                resultMap.put(inputArrays[i], new HuffManNode<String>(inputArrays[i], 0));
            resultMap.get(inputArrays[i]).count++;
        }
        return resultMap;
    }

    //二分查找插入数据，经典算法
    private void sortListNode(List<HuffManNode<String>> listNode, HuffManNode<String> obj) {
        //左右标识，以及初始化
        int left = 0;
        int right = listNode.size() - 1;
        //中间节点
        int middle = (left + right) / 2;
        //循环结束标识
        while (left < right) {
            //重置左右节点和中间节点
            if (obj.count <= listNode.get(middle).count)
                right = middle - 1;
            else
                left = middle + 1;
            //重置相关的数据,不懂为什么使用 >>1 就会出现 middle = -1的情况
            //而使用 /2 结果却是 0 ，奇怪的bug
            middle = (left + right) / 2;
        }
        //开始比较,插入正常的位置
        if (CollectionUtils.isEmpty(listNode))
            listNode.add(obj);
        else if (obj.count <= listNode.get(middle).count)
            listNode.add(middle, obj);
        else
            listNode.add(middle + 1, obj);
    }

    //构建一个哈夫曼树，每次构建一个节点就重新排序
    private HuffManNode<String> toCreateAHuffManTree(List<HuffManNode<String>> listNode) {
        //当List只剩下一个节点的时候，才结束
        while (listNode.size() > 1) {
            //因为已经是一个有序的序列，所以直接取最小的两个数据
            HuffManNode<String> leastNode = listNode.get(0);
            HuffManNode<String> lessNode = listNode.get(1);
            //上面两个节点组成的一个根节点,data为空是为解码
            HuffManNode<String> rootNode = new HuffManNode<String>("", leastNode.count + lessNode.count);
            //左孩子为最小的那个
            rootNode.lchild = leastNode;
            rootNode.rchild = lessNode;
            //从序列中移除上面两个元素
            listNode.remove(0);
            //因为移除了第一，所以第二个就变了第一个了
            listNode.remove(0);
            //将根节点插入到序列中
            sortListNode(listNode, rootNode);
        }

        return listNode.get(0);
    }

    //找出每个单词对应的哈夫曼编码,形式为: a -----> 010100
    private void recurisveFindHuffManCode(HuffManNode<String> rootNode, Map<String, String> wordCode, String code) {
        if (rootNode == null)
            return;
        //递归左孩子
        recurisveFindHuffManCode(rootNode.lchild, wordCode, code + "0");
        //递归右孩子
        recurisveFindHuffManCode(rootNode.rchild, wordCode, code + "1");
        //放当前节点的编码
        wordCode.put(rootNode.data, code);
    }
}
