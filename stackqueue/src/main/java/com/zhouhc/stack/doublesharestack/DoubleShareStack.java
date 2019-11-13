package com.zhouhc.stack.doublesharestack;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: DoubleShareStack
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/8/27 23:38
 */

//双栈共享一个顺序存储空间
public class DoubleShareStack<T> {

    private static Logger LOG = LoggerFactory.getLogger(DoubleShareStack.class);

    private static final int size = 12;

    private int top1 = 0;
    private int top2 = size-1;

    private Object[] element;

    public DoubleShareStack() {
        intiStack();
    }

    private void intiStack(){
        element = new Object[size];
    }

    //添加元素
    public void push(T t,int kind){

        if(top1+1 == top2)
            throw  new RuntimeException("栈已满");

        if(kind == 1)
            element[top1++] = t;
        else
            element[top2--] = t;
    }

    public T pop(int kind){
        T t = null;
        if(kind == 1) {
           t=(T) element[--top1];
           element[top1]=null;
        }
        else {
            t = (T) element[++top2];
            element[top2]=null;
        }

        return t;
    }

    public T getTop(int kind){
        if(kind == 1)
            return (T)element[top1-1];
        else
            return (T)element[top2+1];
    }

}
