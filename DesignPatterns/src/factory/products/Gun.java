package factory.products;

import DesignPatterns.factory.products.Weapon;

/**
 * @author:李罡毛
 * @date:2021/2/13 14:24
 */
public class Gun extends Weapon {
    @Override
    public void shoot() {
        System.out.println(".....Gun:dadadadada......");
    }
}
