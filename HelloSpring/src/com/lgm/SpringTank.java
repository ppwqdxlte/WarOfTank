package com.lgm;

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
}
