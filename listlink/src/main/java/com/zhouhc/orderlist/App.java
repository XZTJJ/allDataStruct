package com.zhouhc.orderlist;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Logger LOG = LogManager.getLogger(App.class);

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    @Test
    public void test(){
        LOG.info("仅仅是测试");
    }
}
