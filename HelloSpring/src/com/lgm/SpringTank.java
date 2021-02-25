package com.lgm;

/**
 * @author:李罡毛
 * @date:2021/2/25 12:34
 */
public class SpringTank {
    private Driver driver;

    public SpringTank(){
        System.out.println("创建了一个SpringTank对象");
    }

    public void move() {
        System.out.println("Tank is moving ....");
    }

    public void fire() {
        System.out.println("Fire a bullet>>>>>>>>>>>>>>>>>");
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }
}
