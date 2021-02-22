package com.lgm.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author:李罡毛
 * @date:2021/2/22 16:31
 */
public class TestV5 {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("app-aop-TestV1.xml");
        Tank tank = (Tank) applicationContext.getBean("aopTank");
        tank.move();
    }
}
