package com.zhouhc.chainlist;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @ClassName: ChainListTest
 * @Description:链式存储结构测试
 * @Author: zhouhc
 * @CreateDate: 2019/8/11 22:16
 */
public class ChainListTest {

    private static Logger LOG = LoggerFactory.getLogger(ChainListTest.class);
    @Test
    public void test(){
        ChainList<String> cl = new ChainList<String>();

        //增加数组测试
        for(int i = 0;i< 8;i++)
            cl.add(i+"",1);

        //打印数组
        LOG.info("数组长度为:"+cl.size()+" , 数组为 ：" +cl.showAll());

        //随机插入元素
        cl.addRand(0,"插入头");
        cl.addRand(3,"插中间");
        cl.addRand(cl.size()-1,"插尾部");
        //打印数组
        LOG.info("数组长度为:"+cl.size()+" , 数组为 ：" +cl.showAll());

        //删除元素
        cl.remove(0);
        cl.remove(2);
        cl.remove(cl.size()-1);
        //打印数组
        LOG.info("数组长度为:"+cl.size()+" , 数组为 ：" +cl.showAll());

        //清空数组
        cl.clearAll();
        //打印数组
        LOG.info("数组长度为:"+cl.size()+" , 数组为 ：" +cl.showAll());

        //添加元素
        for(int i = 0;i< 5;i++)
            cl.add(i+"",-1);
        //打印数组
        LOG.info("数组长度为:"+cl.size()+" , 数组为 ：" +cl.showAll());

        //获取yuans
        LOG.info("首元素 : "+cl.getElement(0)+" ， 中间元素 : "+cl.getElement(3) +" , 尾元素 : "+ cl.getElement(cl.size()-1));
        cl.set(0,"00");
        cl.set(3,"33");
        cl.set(cl.size()-1,"44");
        //打印数组
        LOG.info("数组长度为:"+cl.size()+" , 数组为 ：" +cl.showAll());

        LOG.info("是否包含 00 "+cl.contain("00"));
        LOG.info("是否包含 2 "+cl.contain("2"));
        LOG.info("是否包含 44 "+cl.contain("44"));


    }
}
