package DesignPatterns.factory.abstractFactory;

import DesignPatterns.factory.products.*;

/**
 * @author:李罡毛
 * @date:2021/2/13 15:07
 */
public class MagicFactory extends AbstractFactory{
    @Override
    Vehicle produceVehicle() {
        return new Broom();
    }

    @Override
    Weapon produceWeapon() {
        return new MagicStick();
    }

    @Override
    Food produceFood() {
        return new Mushroom();
    }
}
