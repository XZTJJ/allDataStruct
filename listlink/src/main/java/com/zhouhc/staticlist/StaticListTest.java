package com.zhouhc.staticlist;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: StaticListTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/8/13 23:55
 */
public class StaticListTest {
    private static Logger LOG = LoggerFactory.getLogger(StaticListTest.class);

    @Test
    public void test() {
        //初始化数组
        StaticList<String> sl = new StaticList<String>();
        //添加元素
        for (int i = 1; i <= 3; i++)
            sl.add("第" + i + "个");
        //打印
        LOG.info("length:" + sl.getSize() + ", items : " + sl.showAll());

        //添加元素
        sl.add("头部", 0);
        sl.add("中间", 3);
        sl.add("尾部");
        LOG.info("length:" + sl.getSize() + ", items : " + sl.showAll());

        //删除元素
        sl.remove(0);
        sl.remove(2);
        sl.remove(sl.getSize() - 1);
        LOG.info("length:" + sl.getSize() + ", items : " + sl.showAll());


        //测试删除元素后添加元素
        sl.add("头部", 0);
        sl.add("中间", 3);
        sl.add("尾部");
        LOG.info("length:" + sl.getSize() + ", items : " + sl.showAll());

        //判断数组是否为空
        LOG.info("数组是否为空: " + sl.isEmpty());
        //清空数组
        sl.clear();
        LOG.info("length:" + sl.getSize() + ", items : " + sl.showAll());

        //添加元素
        //添加元素
        for (int i = 1; i <= 3; i++)
            sl.add("第" + i + "个");
        //打印
        LOG.info("length:" + sl.getSize() + ", items : " + sl.showAll());

        //获取某个元素
        LOG.info("最后一个元素: " + sl.getElement(sl.getSize() - 1));
        //修改元素
        sl.setElement("这才是头部", 0);
        LOG.info("length:" + sl.getSize() + ", items : " + sl.showAll());

    }
}
