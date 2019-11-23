package com.zhouhc.huffmanTree;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName: HuffManTreeTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/11/20 21:54
 */
public class HuffManTreeTest {

    //日志类型
    private static Logger LOG = LoggerFactory.getLogger(HuffManTreeTest.class);

    @Test
    public void testHuffManTree() {
        StringBuffer stringBuffer = new StringBuffer();
        //因为65转成char为A,
        int startIndex = 65;
        //表示取A-z之间的字符加符号的58个字符
        int round = 58;
        Random ramdom = new Random();
        for (int i = 0; i < 500; i++) {
            //随机获其中的某个字符
            int nextInt = ramdom.nextInt(round);
            stringBuffer.append(String.valueOf((char) (nextInt + startIndex)));
        }
        String input = stringBuffer.toString();
        //调用函数
        HuffManTree hmt = new HuffManTree();
        //完成构建
        HuffManNode<String> aHuffManTree = hmt.createAHuffManTree(input);
        //对哈夫曼树进行编码工作,找出每个单词对应的哈夫曼编码
        Map<String, String> huffManCode = hmt.codeOfHuffMan(aHuffManTree);
        //对应普通的编码,A-Z的普通编码
        Map<String, String> normalCode = getNormalCode(round,startIndex);

        //打印一下，哈夫曼和普通编码结果
        StringBuffer huffManCodeString = new StringBuffer();
        huffManCodeString.append("哈夫曼编码转换结果: ");
        for (Map.Entry<String, String> tempMap : huffManCode.entrySet())
            huffManCodeString.append(tempMap.getKey()).append("--->").append(tempMap.getValue()).append("  ");
        StringBuffer normalCodeString = new StringBuffer();
        normalCodeString.append("普通编码转换结果: ");
        for (Map.Entry<String, String> tempMap : normalCode.entrySet())
            normalCodeString.append(tempMap.getKey()).append("--->").append(tempMap.getValue()).append("  ");
        LOG.info(huffManCodeString.toString()+"\n"+normalCodeString.toString());

        //转成对应的编码
        huffManCodeString.setLength(0);
        normalCodeString.setLength(0);
        LOG.info("原是数据位:"+input);
        String[] inputArray = input.split("");
        for(int i = 0;i<inputArray.length;i++){
            huffManCodeString.append(huffManCode.get(inputArray[i]));
            normalCodeString.append(normalCode.get(inputArray[i]));
        }
        LOG.info("转成哈夫曼编码为::"+huffManCodeString.toString()+"\n"+"转成普通编码为:"+normalCodeString.toString());
        LOG.info("转换完成:哈夫曼编码长度为:"+huffManCodeString.length()+"普通编码长度为:"+normalCodeString.length());

        //解码工作
        String code = hmt.unCodeOfHuffMan(huffManCodeString.toString(), aHuffManTree);
        LOG.info("原有数据:"+input);
        LOG.info("还原数据:"+code);

    }

    //使用二进制标识，一个二进制位表示2个数，从round变量可以得知需要
    //2的n次方个二级位
    private Map<String, String> getNormalCode(int round, int startIndex) {
        //求取最少的位数问题
        int weishu = 1;
        while (Math.pow(2,weishu) < round)
            weishu++;

        //初始化位数
        String suffix = "";
        for (int i = 0; i < weishu; i++)
            suffix += "0";

        Map<String, String> resultMap = new HashMap<String, String>();
        //编码，可以随意编码，只要一个编码对应一个字符就行了，即一一对应的关系
        for (int i = 0; i < round; i++) {
            //十进制转二进制
            String s = Integer.toBinaryString(i);
            //补0
            s = suffix + s;
            //截取
            s = StringUtils.substring(s, s.length() - weishu, s.length());
            resultMap.put(String.valueOf((char) (i + startIndex)), s);
        }

        return resultMap;
    }
}
