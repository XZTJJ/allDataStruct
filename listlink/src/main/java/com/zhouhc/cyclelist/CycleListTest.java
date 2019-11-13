package com.zhouhc.cyclelist;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.util.locale.provider.LocaleServiceProviderPool;

/**
 * @ClassName: CycleListTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/8/25 18:28
 */
public class CycleListTest {

    private static Logger LOG = LoggerFactory.getLogger(CycleListTest.class);

    @Test
    public void test() {
        //测试
        CycleList<String> cl = new CycleList<String>();
        //添加元素
        for (int i = 1; i <= 3; i++)
            cl.add("第" + i + "个");
        //答应数组
        LOG.info("length:" + cl.getSize() + " ,item:" + cl.showAll());
        //第一个元素和最后一个元素
        LOG.info("第一个元素:" + cl.getFirstElement() + " ,最后一个元素:" + cl.getLastElement());

        //随机插入元素
        cl.add("头部", 0);
        cl.add("中间", 3);
        cl.add("尾部", cl.getSize());
        LOG.info("length:" + cl.getSize() + " ,item:" + cl.showAll());
        LOG.info("第一个元素:" + cl.getFirstElement() + " ,最后一个元素:" + cl.getLastElement());

        //删除元素
        cl.remove(0);
        cl.remove(2);
        cl.remove(cl.getSize() - 1);
        LOG.info("length:" + cl.getSize() + " ,item:" + cl.showAll());
        LOG.info("第一个元素:" + cl.getFirstElement() + " ,最后一个元素:" + cl.getLastElement());

        //第一个元素和最后一个元素
        LOG.info("第一个元素:" + cl.getElement(0) + " ,最后一个元素:" + cl.getElement(cl.getSize() - 1));

        //修改元素
        cl.setElement("这才是头部", 0);
        cl.setElement("这才是中间", 1);
        cl.setElement("这才是尾部", 2);
        LOG.info("length:" + cl.getSize() + " ,item:" + cl.showAll());

        //拼接测试
        CycleList<String> c2 = new CycleList<String>();
        for (int i = 1; i <= 3; i++)
            c2.add("第" + i + "个");

        cl.appendCycList(c2);
        LOG.info("length:" + cl.getSize() + " ,item:" + cl.showAll());
        LOG.info("第一个元素:" + cl.getFirstElement() + " ,最后一个元素:" + cl.getLastElement());
    }
}
