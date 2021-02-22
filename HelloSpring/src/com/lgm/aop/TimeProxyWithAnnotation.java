package com.lgm.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author:李罡毛
 * @date:2021/2/22 18:03
 */
@Aspect
public class TimeProxyWithAnnotation {

    @Before("execution(void com.lgm.aop.Tank.move())")
    public void before() {
        System.out.println("method start.." + System.currentTimeMillis());
    }
    @After("execution(void com.lgm.aop.Tank.move())")
    public void after() {
        System.out.println("method stop.." + System.currentTimeMillis());
    }
}
