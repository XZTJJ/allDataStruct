package com.zhouhc.staticlist;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * @ClassName: StaticList
 * @Description: 静态链表
 * @Author: zhouhc
 * @CreateDate: 2019/8/13 23:12
 */

/*
 *
 *静态链表的实现方式与书上的有些不同，可以支持扩容
 * 不过不推荐使用这种方式，浪费空间和性能,
 * 本例使用的是第一个元素作为备用链表的标记节点，第二个元素作为头节点，使用动态扩容的方式
 */

public class StaticList<T> {

    //日志文件
    private static Logger LOG = LogManager.getLogger(StaticList.class);
    //存储数据
    private transient Object[] tNode;
    //默认长度
    private static final int DEL = 10;
    //数组大小
    private int size;
    //空数组
    private static final Object[] dtNode = {};


    public StaticList() {
        initStaticLink();
    }

    //添加元素
    public void add(T t) {
        //获取存储空间
        int j = getSpace();
        //检查是否需要扩容
        ensurexpansion(j + 1);
        //获取初始化的元原下标
        int k = 1;
        //获取最后一个节点的位置
        while (getNextIndex(k) != 0)
            k = getNextIndex(k);
        //修改最后上一次最后一个元素的下标
        ((Node<T>) tNode[k]).next = j;
        //创建元素，并作为数组的最后一个元素，因为是动态扩容的，所以每次
        //创建的时间都要自动赋值为0
        Node<T> temp = new Node<T>(t, 0);
        tNode[j] = temp;
        size++;
    }

    //随机插入元素
    public void add(T t, int index) {
        //获取空间位置
        int j = getSpace();
        //检查是否需要扩容
        ensurexpansion(j + 1);
        //找到上个元素
        int k = 1;
        for (int i = 0; i < index; i++)
            k = getNextIndex(k);

        //创建新元素
        Node<T> temp = new Node<>(t, getNextIndex(k));
        tNode[j] = temp;
        //修改上个元素的下标
        ((Node<T>) tNode[k]).next = j;

        size++;
    }

    //删除元素
    public void remove(int index) {
        //找个要删除元素的前一个元素
        int k = 1;
        for (int i = 0; i < index; i++)
            k = getNextIndex(k);
        //要删除元素的索引
        int l = getNextIndex(k);
        //调成数组,去掉要删除的元素
        ((Node<T>) tNode[k]).next = ((Node<T>) tNode[l]).next;
        //模拟释放空间
        freeSpace(l);
        size--;
    }

    //判断数组是否为空
    public boolean isEmpty(){
        return size <= 2;
    }

    //清空数组
    public void clear(){
        for(int i = 0;i<size;i++)
            tNode[i] = null;
        //初始化
        initStaticLink();
    }

    //获取某个元素
    public T getElement(int index){
        //检查数组是否越界
        checkRange(index);
        //获取元素,是找到当前元素
        int k = 1;
        for(int i = 0;i<=index;i++)
            k = getNextIndex(k);

        return (T)getContent(k);
    }

    //修改某个元素的值
    public void setElement(T t,int index){
        //检查数组是否越界
        checkRange(index);
        //获取元素,是找到当前元素
        int k = 1;
        for(int i = 0;i<=index;i++)
            k = getNextIndex(k);

        ((Node<T>) tNode[k]).t = t;
    }


    private void freeSpace(int index) {
        //帮助GC清空元素
        Node<T> o  = (Node<T>)tNode[index];
        o = null;
        //清空元素
        tNode[index] = new Node<T>(null,-2);
        //修改数组的相关下标
        ((Node<T>) tNode[index]).next = ((Node<T>) tNode[0]).next;
        ((Node<T>) tNode[0]).next = index;
    }

    //获取空闲下标。模拟动态内存的分配空间
    private int getSpace() {
        int i = getNextIndex(0);
        //-1表示没有空闲数组,直接添加都扩容的空间中
        if (i != -1)
            ((Node<T>) tNode[0]).next = ((Node<T>) tNode[i]).next;
        else
            i = size;
        return i;
    }

    //确认是否扩容
    private void ensurexpansion(int minCount) {

        if (minCount - tNode.length > 0)
            grow(minCount);
    }

    //数组扩容
    private void grow(int minCount) {
        int oldLength = tNode.length;

        int newlength = oldLength + (oldLength >> 1);

        if (newlength > minCount)
            newlength = minCount;

        tNode = Arrays.copyOf(tNode, newlength);
    }

    //数组的初始化，第一个元素为放有空字符的数据 ，第二个元素为放有数据的下标
    private void initStaticLink() {
        tNode = new Object[10];
        Node<T> tHead = new Node<T>(null, -1);
        Node<T> tTial = new Node<T>(null, 0);
        tNode[0] = tHead;
        tNode[1] = tTial;
        size = 2;
    }

    //获取数组的长度
    public int getSize() {
        return size - 2;
    }

    //检查数组是否越界
    private void checkRange(int index) {
        if (index < 0 || index >= size - 2)
            throw new RuntimeException("数组越界");
    }

    //打印数组
    public String showAll() {

        if (size <= 2)
            return "[]";

        StringBuffer sb = new StringBuffer();
        sb.append("[");
        int k = 1;

        while (getNextIndex(k) != 0) {
            k = getNextIndex(k);
            sb.append(getContent(k)).append(",");
        }

        sb.delete(sb.length() - 1, sb.length());
        sb.append("]");

        return sb.toString();
    }

    //获取索引
    private int getNextIndex(int index) {
        Node<T> temp = (Node<T>) tNode[index];
        return temp.next;
    }

    //获取内容
    private T getContent(int index) {
        Node<T> temp = (Node<T>) tNode[index];
        return (T) temp.t;
    }

    //内部存储元素
   private class Node<T> {
        T t;
        int next;

        public Node(T t, int next) {
            this.t = t;
            this.next = next;
        }
    }

}
