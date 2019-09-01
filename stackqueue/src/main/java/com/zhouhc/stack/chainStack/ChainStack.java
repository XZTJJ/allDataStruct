package com.zhouhc.stack.chainStack;

/**
 * @ClassName: ChainStack
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/8/31 23:53
 */

//链式存储的栈结构
public class ChainStack<T> {

    private int size;

    private Node topNode;

    public ChainStack() {
        initStack();
    }

    //添加元素的操作
    public void push(T t) {
        //初始化数据
        Node temp = new Node(t, topNode.next);
        topNode.next = temp;
        size++;
    }

    //弹出元素操作
    public T pop() {
        Node next = topNode.next;
        topNode.next = next.next;
        size--;
        return (T) next.t;
    }

    //获取栈顶元素
    public T getTop() {
        return (T) topNode.next.t;
    }

    //获取大小
    public int getSize() {
        return size;
    }

    //初始化操作，创建topNode，主要是为了方便
    private void initStack() {
        topNode = new Node(null, null);
        size = 0;
    }


    //内部存储结构
    private class Node<T> {
        T t;
        Node next;

        public Node(T t, Node next) {
            this.t = t;
            this.next = next;
        }
    }
}
