package com.zhouhc.stack.arithmeticstack;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Scanner;

/**
 * @ClassName: ArithmeticStackTest
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/9/1 23:09
 */

//测试数据, 表达式暂时只支持+-*/()这些运算符
public class ArithmeticStackTest {

    private static Logger LOG = LogManager.getLogger(ArithmeticStackTest.class);


    //后缀表达式输入
    @Test
    public void testSubfix() {
        String express = "9.0+(3-1)*3+10/2";
        //String express = "9.0+(3-1)*(3-1)+10/2";
        //String express = "9.0+(3-1)*(3-1+10/2";
        //String express = "9.0";
        //String express = "-9.0+(10-2)/1";
        //String express = "+9.0+(10-2)/1";
        //调用数字
        ArithmeticStack ats = new ArithmeticStack();
        Double result = ats.getResult(express);

        LOG.info(express + "=" + result);
    }
}
