package com.zhouhc.queue.chainqueue;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ChainQueue
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/9/16 21:58
 */
public class ChainQueue<T> {
    //日志
    private static Logger LOG = LoggerFactory.getLogger(ChainQueue.class);
    //头尾指针
    private Node headNode;
    private Node tailNode;
    //链表大小
    private int size;

    //构造函数
    public ChainQueue() {
        initQueue();
    }

    //入队
    public void add(T t) {
        Node temp = new Node(t, null);
        tailNode.next = temp;
        tailNode = temp;
        size++;
    }

    //出队
    public T remove() {
        if (isEmpty())
            throw new RuntimeException("数组越界");
        T t = (T) headNode.next.t;
        headNode.next = headNode.next.next;
        size--;
        return t;
    }

    //大小和是否为空
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() <= 0;
    }

    public String showAll() {
        if (size == 0)
            return "[]";

        Node temp = headNode.next;
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        while (temp != null) {
            sb.append(temp.t.toString()).append(",");
            temp = temp.next;
        }

        sb.delete(sb.length() - 1, sb.length());
        sb.append("]");
        return sb.toString();
    }

    //初始化
    private void initQueue() {
        size = 0;
        headNode = new Node(null, null);
        tailNode = headNode;
    }

    //内部节点
    private class Node<T> {
        T t;
        Node next;

        public Node(T t, Node next) {
            this.t = t;
            this.next = next;
        }
    }
}
