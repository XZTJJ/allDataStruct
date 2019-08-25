package com.zhouhc.doublelist;

/**
 * @ClassName: DoubleList
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/8/25 22:45
 */

//双向的循环链表
public class DoubleList<T> {

    //数据大小
    private int size;
    //头节点
    private Node headNode;

    //构造函数
    public DoubleList() {
        initList();
    }

    //插入数据
    public void add(T t) {
        //创建元素，并且设置前驱和后继
        Node<T> tempItem = new Node<T>(t, headNode, headNode.next);
        //修改headNode的后继的前驱为新元素
        headNode.next.prior = tempItem;
        //修改headNode的后继为新创建的元素
        headNode.next = tempItem;
        size++;
    }

    //随机添加数组
    public void add(T t, int index) {
        //添加元素做特殊处理
        Node temp = headNode;
        //使用next的方法
        if (index < (size / 2)) {
            //定位到前一个元素
            for (int i = 0; i < index; i++)
                temp = temp.next;
            //开始插入元素
            Node<T> tempItem = new Node<T>(t, temp, temp.next);
            temp.next.prior = tempItem;
            temp.next = tempItem;
        }
        //使用prior的方法
        else {
            //定位到要插入元素的下一个元素的位置
            for (int i = size; i > index; i--)
                temp = temp.prior;
            //创建元素
            Node<T> tempItem = new Node<T>(t, temp.prior, temp);
            temp.prior.next = tempItem;
            temp.prior = tempItem;
        }
        size++;
    }

    //移除某个元素
    public T remove(int index) {
        //数组越界
        checkRange(index);

        Node temp = headNode;
        T t = null;
        //移除某个元素,使用next的方式
        if (index < (size / 2)) {
            //定位到前一个元素
            for (int i = 0; i <= index; i++)
                temp = temp.next;
            t = (T) temp.t;
            temp.prior.next=temp.next;
            temp.next.prior = temp.prior;
        }
        //移除某个元素，使用prior的方式
        else {
            //定位到后一个元素
            for (int i = size; i > index; i--)
                temp = temp.prior;

            t = (T) temp.t;
            temp.prior.next=temp.next;
            temp.next.prior = temp.prior;
        }
        size--;
        return t;
    }

    //获取某个元素
    public T getElement(int index){
        checkRange(index);
        Node temp =headNode;
        T t = null;
        //小于，走next的
        if(index < (size/2)){
            //定位到本元素
            for (int i = 0;i<=index;i++)
                temp = temp.next;
        }else{
            for(int i =size;i>index;i--)
                temp = temp.prior;
        }
        t = (T)temp.t;
        return t;
    }

    //设置某个元素
    public void setElement(T t,int index){
        checkRange(index);
        Node temp =headNode;
        //小于，走next的
        if(index < (size/2)){
            //定位到本元素
            for (int i = 0;i<=index;i++)
                temp = temp.next;
        }else{
            for(int i =size;i>index;i--)
                temp = temp.prior;
        }
        temp.t = t;
    }

    //判断数组是否越界
    public void checkRange(int index) {
        if (size < 0 || index >= size)
            throw new RuntimeException("数组越界");
    }

    //获取数组大小
    public int getSize() {
        return size;
    }

    //初始化
    private void initList() {
        headNode = new Node(null, null, null);
        headNode.next = headNode;
        headNode.prior = headNode;
    }

    //打印数组
    public String showAll() {
        if (size == 0)
            return "[]";
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        //初始化元素
        Node temp = headNode.next;
        while (temp != headNode) {
            sb.append(temp.t).append(",");
            temp = temp.next;
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append("]");
        return sb.toString();
    }

    //内部存储结构
    private class Node<T> {
        //数据域
        T t;
        //前驱
        Node prior;
        //后继
        Node next;

        //构造函数
        public Node(T t, Node prior, Node next) {
            this.t = t;
            this.prior = prior;
            this.next = next;
        }
    }
}
