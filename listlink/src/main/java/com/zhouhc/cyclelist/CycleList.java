package com.zhouhc.cyclelist;

/**
 * @ClassName: CycleList
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/8/25 18:04
 */

//实现循环链表操作
public class CycleList<T> {
    //初始化数组的大小
    private int size;
    //头节点，为了方便后面的插入一律用头插法的方式
    //本实例使用尾插法要简单一些
    private Node<T> headNode;
    //尾指针
    private Node<T> tailPointer;

    //构造函数
    public CycleList() {
        initList();
    }

    //添加元素,添加元素的话，始终添加在头部
    public void add(T t) {
        //指向头节点的下一个节点
        Node<T> temp = new Node<T>(t, headNode.next);
        //修改头指针的指向问题
        headNode.next = temp;
        //修改为尾指针,尾指针始终指向最后一个
        //因为采用头插法，所以指向第一个插入的
        //元素就好了
        if (temp.next == headNode)
            tailPointer = temp;
        size++;
    }

    //随机添加元素
    public void add(T t, int index) {
        //判断数组是否越界
        if (index < 0 || index > size)
            throw new RuntimeException("数组越界");
        //元素的添加
        Node temp = headNode;
        //元素的题添加
        for (int i = 0; i < index; i++)
            temp = temp.next;
        //创建元素
        Node<T> newItem = new Node<T>(t, temp.next);
        temp.next = newItem;
        //失踪保持指向最后一个元素
        if (size == index)
            tailPointer = newItem;
        size++;
    }

    //元素的删除
    public T remove(int index) {
        checkRange(index);
        Node temp = headNode;
        //定位该元素
        for (int i = 0; i < index; i++)
            temp = temp.next;
        //cur的主要作用是Help Gc
        Node cur = temp.next;
        T t = (T) cur.t;
        temp.next = cur.next;
        //数据元素
        if (size == index + 1)
            tailPointer = temp;
        cur = null;
        size--;
        return t;
    }

    //获取元素
    public T getElement(int index) {
        checkRange(index);

        Node temp = headNode;

        for (int i = 0; i <= index; i++)
            temp = temp.next;

        return (T) temp.t;
    }

    //修改元素
    public void setElement(T t, int index) {
        checkRange(index);

        Node temp = headNode;

        for (int i = 0; i <= index; i++)
            temp = temp.next;

        temp.t = t;
    }

    //两个链表链接
    public void appendCycList(CycleList cl){
        //用于构成一个循环，用cl的尾部指向本对象的头节点的下一个节点
        cl.tailPointer.next = headNode.next;
        //将本身实的尾部指向cl的头部
        tailPointer.next = cl.headNode;
        //用于把值交换过来
        headNode = cl.headNode;

        //处理元素的个数
        size += cl.size;
    }


    //判断是否越界
    public void checkRange(int index) {
        if (index < 0 || index >= size)
            throw new RuntimeException("数组越界");
    }

    //获取元素的大小
    public int getSize() {
        return size;
    }

    //获取第一个元素
    public T getFirstElement() {
        return (T) tailPointer.next.next.t;
    }

    //获取最后一个元素
    public T getLastElement() {
        return (T) tailPointer.t;
    }

    //打印数组
    public String showAll() {
        if (size == 0)
            return "[]";

        Node<T> temp = headNode.next;
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        while (temp != headNode) {
            sb.append(temp.t).append(",");
            temp = temp.next;
        }

        sb.delete(sb.length() - 1, sb.length());
        sb.append("]");

        return sb.toString();
    }

    //初始化处理
    private void initList() {
        headNode = new Node<T>(null, null);
        //设置循环结构
        headNode.next = headNode;
        //尾指针指向最后一个元素
        tailPointer = headNode;
        //大小置为0
        size = 0;
    }

    //内部存储结构
    private class Node<T> {
        //指针域
        T t;
        //静态域
        Node next;

        //构造函数,为了方便就不设置set和get方法
        public Node(T t, Node next) {
            this.t = t;
            this.next = next;
        }
    }

}
