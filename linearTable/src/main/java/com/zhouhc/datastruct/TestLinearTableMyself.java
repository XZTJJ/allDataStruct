package com.zhouhc.datastruct;

import com.zhouhc.lineartablemyself.LinearTableMyself;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Arrays;


/**
 * @ClassName: TestLinearTableMyself
 * @Description: TODO
 * @Author: zhouhc
 * @CreateDate: 2019/6/4 0:28
 */
public class TestLinearTableMyself {
    private  static Logger LOGGER = LogManager.getLogger(TestLinearTableMyself.class);

    public TestLinearTableMyself() {

    }

    //测试
    public static void main(String[] args){
        //开始测试
        //清空测试
        LinearTableMyself<String> clearLinear = new LinearTableMyself<String>();
        int length= clearLinear.getLength();
        clearLinear.addElement("第一次");
        clearLinear.addElement("第二次");
        clearLinear.addElement("第三次");
        length = clearLinear.getLength();
        //array
        Object[] allElement = clearLinear.getAllElement();
        LOGGER.info("数组长度: " + length +" 数据元素 :" + Arrays.toString(allElement));
        //clear
        clearLinear.clear();
        length = clearLinear.getLength();
        allElement = clearLinear.getAllElement();
        LOGGER.info("数组长度: " + length +" 数据元素 :" + Arrays.toString(allElement));
        clearLinear.addElement("第一次");
        clearLinear.addElement("第二次");
        length = clearLinear.getLength();
        allElement = clearLinear.getAllElement();
        LOGGER.info("数组长度: " + length +" 数据元素 :" + Arrays.toString(allElement));

    }

    @Test
    public void testMaven(){
        LOGGER.info("logger 日志测试");

        for(int i =0;i<9;i++){
            try {
                if (i % 3 == 0)
                    throw  new RuntimeException("整除3的错误");
                LOGGER.info("第"+i+"次");
            }catch (Exception e){
                LOGGER.error("出错了"+e.getMessage(),e);
            }

        }

    }

}
