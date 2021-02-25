package com.lgm.ASM;

/**
 * @author:李罡毛
 * @date:2021/2/22 18:03
 */
public class TimeProxy {
    public static void before() {
        System.out.println("method start.." + System.currentTimeMillis());
    }
    public static void after() {
        System.out.println("method stop.." + System.currentTimeMillis());
    }
}
