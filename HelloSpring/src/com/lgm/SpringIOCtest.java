package com.lgm;

import org.springframework.context.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author:李罡毛
 * @date:2021/2/16 13:35
 */
public class SpringIOCtest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        applicationContext.getBean("tank");

    }
}
