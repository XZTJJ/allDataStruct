package com.zhouhc.orderlist;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @ClassName: OrderListTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/8/9 21:44
 */
public class OrderListTest {
    private static Logger LOG = LogManager.getLogger(OrderListTest.class);

    @Test
    public void testOrder() {
        OrderList<String> od = new OrderList<String>();

        LOG.info("是否为空 : " + od.emptyList());

        //添加元素
        for (int i = 0; i < 20; i++) {
            String temp = new String(i+"");
            od.add(temp);
        }
        LOG.info("输出数组 : " + od);
        LOG.info("是否为空 : " + od.emptyList());

        //清空数据
        od.cleanList();
        LOG.info("是否为空 : " + od.emptyList());
        LOG.info("输出数组 : " + od);

        //添加数组
        //添加元素
        for (int i = 0; i < 20; i++) {
            String temp = new String(i+"");
            od.add(temp);
        }
        LOG.info("输出数组 : " + od);
        LOG.info("是否为空 : " + od.emptyList());

        //修改数组值
        String oleValue = od.set(19,new String(200+""));
        LOG.info("输出数组 : " + od +"原始值为 : " +oleValue);

        //测试是否包含
        LOG.info("是否包含 5 : " + od.contain(new String(5+"")));

        od.add("500");
        //删除元素
        LOG.info("原数组 : " + od);
        int index = od.size();
        for(int i = 0;i<index;i++) {
            String t = od.remove(0);
            LOG.info("删除索引为" + 0 + "数组 : " + od);
        }

        //数组越界测试
        //od.getElement(100);
        //od.remove(100);

        //任意位置插入
        index = 10;
        od.add(index,"300");
        LOG.info("在下标为"+index+"插入的数组 : " + od);

    }
}
