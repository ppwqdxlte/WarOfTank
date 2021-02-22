package com.lgm.aop;

import java.util.Random;

/**
 * @author:李罡毛
 * @date:2021/2/22 17:35
 */
public class Tank {
    public void move(){
        System.out.println("Tank is moving");
        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
