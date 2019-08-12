package com.zhouhc.orderlist;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * @ClassName: OrderList
 * @Description: 随机存储结构基本
 * @Author: zhouhc
 * @CreateDate: 2019/8/9 20:32
 */
public class OrderList<T> {

    private static Logger LOG = LogManager.getLogger(OrderList.class);

    //默认长度
    private final static int DEFAULT_LENGTH = 10;

    //默认为空的数组
    private final static Object[] DEFAULT_INIT={};

    //声明数组
    transient  Object[] elementData ;

    //数组长度,数组本身的长度(elementData.length)永远大于的等于size
    private int size ;

    //初始化默认数组长度
    public OrderList() {
        elementData = DEFAULT_INIT;
    }

    //添加数据元素
    public void add(T e){
        //用于计算数组是否需要扩容
       ensurexpansion(size+1);
       elementData[size++] = e;
    }

    public void add(int index,T e){
        ensurexpansion(size+1);
        //开始
        System.arraycopy(elementData,index,elementData,index+1,(size-index));
        elementData[index] = e;
        size++;
    }

    public void ensurexpansion(int mincount){
        //判断数组是否为空
       if(elementData == DEFAULT_INIT){
           mincount = Math.max(DEFAULT_LENGTH,mincount);
       }

        //判断是否需要扩容,大于零才表示需要扩容，最坏的情况是elementData.length-1为放置新元素
        if(mincount - elementData.length > 0)
            grow(mincount);

    }

    public void grow(int mincount){
        //下面所有数据都不需要考虑类型溢出问题
        int oldLenght = elementData.length;
        int newLength = oldLenght + (oldLenght >> 1);

        //如果新长度比mincount小，让mincount为新的长度
        if(newLength-mincount<0)
            newLength = mincount;
        //扩容
        elementData = Arrays.copyOf(elementData,newLength);
        LOG.info("数组扩容了，数组本身长度为 : "+elementData.length+"，size的长度为 : "+ size);
    }

    //获取数组的长度
    public int size(){
        return size;
    }

    //取值
    public T getElement(int i){
        //不管是小于0，还是等于数组的长度这都是不合的
        rangeCheck(i);
        return (T) elementData[i];
    }

    //检查数组是否越界
   private void rangeCheck(int index){
       if(index<0 || index >= size)
           throw new RuntimeException("索引值不合理");
   }

    //查找类型，自定义类类型需要自己复写equal方法和
    public boolean contain(T e){
        for(int i = 0; i<size;i++){
            if(e.equals(elementData[i]))
                return true;
        }

        return false;
    }

    //比较某个位置相的数是否相等
    public boolean isequals(int index,T e){
          return getElement(index).equals(e);
    }

    //清空数组
    public void cleanList(){
        //数组清空
        for(int i=0;i<size;i++)
            elementData[i]=null;
        size = 0;
    }

    //判断数组是否为空
    public boolean emptyList(){
        return size == 0;
    }

    //移除数组
    public T remove(int index){
       //移除数据元素
       T e = getElement(index);
       //数组的溢出
        System.arraycopy(elementData,index+1,elementData,index,(size-index-1));
        elementData[--size] = null;
        return e;
    }

    //设置数据
    public T set(int i,T e){
        T t = getElement(i);
        elementData[i]=e;
        return t;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elementData,size));
    }
}
