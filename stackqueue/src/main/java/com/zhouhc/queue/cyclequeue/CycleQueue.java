package com.zhouhc.queue.cyclequeue;

/**
 * @ClassName: CycleQueue
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/9/4 22:44
 */

/*循环队列，循环队列需要记录的队列长度，数组的长度可以改表
 * 还需要一个队列头指针和队列尾的指针,数组的实际的长度比Length要小1
 */
public class CycleQueue<T> {
    //数组
    private transient Object[] element;
    //默认长度
    private static int DLENGTH = 5;
    //对头
    private int head, tail;


    public CycleQueue() {
        initQueue();
    }

    //添加元素
    public void add(T t) {
        isFull();
        element[tail] = t;
        tail = (tail + 1) % DLENGTH;
    }

    public T remove() {
        if (getSize() == 0)
            throw new RuntimeException("队列为空");
        T t = (T) element[head];
        element[head] = null;
        head = (head + 1) % DLENGTH;
        return t;
    }

    //检查数组是否满了
    public void isFull() {
        if ((tail + 1) % DLENGTH == head)
            throw new RuntimeException("队列已满");
    }

    //获取数组的大小
    public int getSize() {
        return (tail - head + DLENGTH) % DLENGTH;
    }

    //初始化操作
    private void initQueue() {
        //初始化操作
        element = new Object[DLENGTH];
        head = tail = 0;
    }

    //打印数组
    public String showAll() {
        if (getSize() == 0)
            return "[]";

        StringBuffer sb = new StringBuffer();
        sb.append("[");

        int temp = head;
        while (temp != tail) {
            sb.append(element[temp]).append(",");
            temp = (temp + 1) % DLENGTH;
        }
        sb.delete(sb.length()-1,sb.length());
        sb.append("]");
        return sb.toString();
    }
}
