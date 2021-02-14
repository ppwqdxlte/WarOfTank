package factory.abstractFactory;

import DesignPatterns.factory.products.*;

/**
 * @author:李罡毛
 * @date:2021/2/13 15:04
 */
public abstract class AbstractFactory {
    abstract Vehicle produceVehicle();
    abstract Weapon produceWeapon();
    abstract Food produceFood();
}
