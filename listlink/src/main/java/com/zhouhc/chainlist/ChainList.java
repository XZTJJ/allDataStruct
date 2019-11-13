package com.zhouhc.chainlist;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

/**
 * @ClassName: ChainList
 * @Description: 链式存储结果基本
 * @Author: zhouhc
 * @CreateDate: 2019/8/11 21:06
 */
public class ChainList<T> {

    private static Logger LOG = LoggerFactory.getLogger(ChainList.class);

    //数组大小表示
    private int size;
    //第一个节点,初始化赋值方便操作
    private Node<T> headNode = new Node<T>(null, null);
    //最后一个节点
    private Node<T> tailNode;

    //构造函数
    public ChainList() {
    }

    //添加元素,order大于等于零调用头插头，小于零调用尾插法
    public void add(T e, int order) {
        //构造数据元素
        Node<T> tNode = new Node<>(e, null);
        //头插法和尾插法可以随意切换
        if (order >= 0)
            headAdd(tNode);
        else
            tailAdd(tNode);

        //元素加 1
        size++;
    }

    public void addRand(int index, T e) {
        Node<T> currentNode = headNode;
        for (int i = 0; i < index; i++)
            currentNode = currentNode.next;
        Node<T> temp = new Node<T>(e, null);
        temp.next = currentNode.next;
        currentNode.next = temp;
        size++;
    }

    //头插法
    private void headAdd(Node<T> tNode) {
        tNode.next = headNode.next;
        headNode.next = tNode;
    }

    //尾插法
    private void tailAdd(Node<T> tNode) {
        //失踪需要判断
        if (tailNode == null) {
            tailNode = headNode.next = tNode;
        } else {
            //不为空的情况
            tailNode.next = tNode;
            tailNode = tNode;
        }
    }

    //删除元素
    public T remove(int index) {

        rangeCheck(index);

        //保留上一个和当前节点
        Node<T> preNode = headNode;
        Node<T> currentNode = preNode.next;
        for (int i = 0; i < index; i++) {
            preNode = currentNode;
            currentNode = currentNode.next;
        }
        preNode.next = currentNode.next;
        T data = currentNode.data;
        currentNode = null;
        size--;
        return data;
    }

    //初始化元素,主要是为了headNode操作
    private void initheadTail() {
        tailNode = headNode = null;
        headNode = new Node<T>(null, null);
    }

    //清空数组
    public void clearAll() {
        Node<T> tempNode = headNode;
        Node<T> tempNodeV2 = headNode;
        //用于标记下一个
        for (int i = 0; i < size; i++) {
            tempNode = tempNodeV2.next;
            tempNodeV2 = null;
            tempNodeV2 = tempNode;
        }
        size = 0;
        initheadTail();
    }

    //获取地i个yuans
    public T getElement(int index) {
        rangeCheck(index);
        Node<T> current = headNode.next;

        for (int i = 0; i < index; i++)
            current = current.next;

        return (T) current.data;
    }

    //设置元素
    public T set(int index, T e) {
        rangeCheck(index);
        Node<T> current = headNode.next;

        for (int i = 0; i < index; i++)
            current = current.next;

        T t = current.data;
        current.data = e;
        return t;
    }

    //判断是否包含
    public boolean contain(T e) {
        Node<T> current = headNode.next;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(e))
                return true;

            current = current.next;
        }

        return false;
    }

    //判断是否越界
    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new RuntimeException("索引不合理");
    }

    //数组大小
    public int size() {
        return size;
    }

    //数组打印
    public String showAll() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        Node<T> currentNode = headNode.next;
        for (int i = 0; i < size; i++) {
            buffer.append(currentNode.data).append(",");
            currentNode = currentNode.next;
        }
        if (buffer.length() > 1)
            buffer.delete(buffer.length() - 1, buffer.length());
        buffer.append("]");

        return buffer.toString();
    }

    //内部类用于创建节点
    private class Node<T> {
        //数据域
        private T data;
        //指针域
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }
}
