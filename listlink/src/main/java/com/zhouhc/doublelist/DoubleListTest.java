package com.zhouhc.doublelist;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: DoubleListTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/8/25 23:07
 */
public class DoubleListTest {
    private static Logger LOG = LoggerFactory.getLogger(DoubleListTest.class);

    @Test
    public void test(){
        //创建某个元素
        DoubleList<String> dl = new DoubleList<String>();
        //添加元素
        for(int i=1;i<=3;i++)
            dl.add("第"+i+"个");
        //打印数组
        LOG.info("length:"+dl.getSize()+" ,item:"+dl.showAll());
        //随机添加数组
        dl.add("头部",0);
        dl.add("尾部",dl.getSize());
        dl.add("中间",2);
        LOG.info("length:"+dl.getSize()+" ,item:"+dl.showAll());

        //删除某个元素
        dl.remove(0);
        dl.remove(dl.getSize()-1);
        dl.remove(1);
        LOG.info("length:"+dl.getSize()+" ,item:"+dl.showAll());

        //随机添加数组
        dl.add("头部",0);
        dl.add("尾部",dl.getSize());
        dl.add("中间",2);
        LOG.info("length:"+dl.getSize()+" ,item:"+dl.showAll());

        //获取第一个和最后一个元素
        LOG.info("第一:"+dl.getElement(0)+" ,最后:"+dl.getElement(dl.getSize()-1));
        LOG.info("第二:"+dl.getElement(1)+" ,倒二:"+dl.getElement(dl.getSize()-2));

        //设置元素
        dl.setElement("这才是头部",0);
        dl.setElement("这才是尾部",dl.getSize()-1);
        dl.setElement("这才是第二",1);
        dl.setElement("这才是倒二",dl.getSize()-2);
        LOG.info("length:"+dl.getSize()+" ,item:"+dl.showAll());
    }
}
