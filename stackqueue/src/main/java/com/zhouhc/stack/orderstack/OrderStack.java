package com.zhouhc.stack.orderstack;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * @ClassName: OrderStack
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/8/26 23:41
 */

//顺序存储结构的栈
public class OrderStack<T> {

    private static Logger LOG = LogManager.getLogger(OrderStack.class);

    //用于标记栈顶,也可以用于标记数组的大小
    private int top;
    //数据存储元素
    private transient Object[] elementData;
    //默认数组的大小
    private static final int DEL = 10;

    public OrderStack() {
        initStack();
    }
    //开始插入元素
    public void push(T t){
        //查看是否需要扩容
        ensureExpansion(top+1);
        elementData[top++] = t;
    }

    //获取栈顶元素
    public T getTop(){
        return (T)elementData[top-1];
    }

    //删除栈顶元素
    public T pop(){
        T t = (T)elementData[--top];
        elementData[top] = null;
        return t;
    }

    //获取数组大小
    public int getSize(){
        return top;
    }

    //主要用于扩容
    private void ensureExpansion(int minCount){

        if(minCount-elementData.length > 0)
            grow(minCount);
    }

    private void grow(int minCount){

        int oldLength = elementData.length;
        int newLength = oldLength+(oldLength>>1);

        if(newLength - minCount < 0)
            newLength = minCount;

        elementData = Arrays.copyOf(elementData,newLength);
    }


    //初始化数组
    private void initStack() {
        elementData = new Object[DEL];
    }

}
