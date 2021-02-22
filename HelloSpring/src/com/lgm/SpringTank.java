package com.lgm;

import java.util.Random;

/**
 * @author:李罡毛
 * @date:2021/2/16 14:19
 */
public class SpringTank {
    private Driver driver;

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public SpringTank(){
        System.out.println("创建了一个SpringTank对象");
    }

    public void move(){
        System.out.println("Tank is moving......claclaclaclaclaclacla.......");
        try {
            Thread.sleep(new Random().nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void fire(){
        System.out.println(" fire>>>>>>>>>>>>>>>>>");
    }
}
