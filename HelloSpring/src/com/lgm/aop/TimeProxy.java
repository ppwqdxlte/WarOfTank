package com.lgm.aop;

/**
 * @author:李罡毛
 * @date:2021/2/22 18:03
 */
public class TimeProxy {
    public void before() {
        System.out.println("method start.." + System.currentTimeMillis());
    }
    public void after() {
        System.out.println("method stop.." + System.currentTimeMillis());
    }
}
