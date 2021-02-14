package factory.products;

import DesignPatterns.factory.products.Vehicle;

/**
 * @author:李罡毛
 * @date:2021/2/13 14:20
 */
public class Broom extends Vehicle {
    @Override
    public void go() {
        System.out.println("......Broom:shuashuashua...");
    }
}
