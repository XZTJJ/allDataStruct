package com.zhouhc.orderlist;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    @Test
    public void test(){
        LOG.info("仅仅是测试");
    }
}
