package factory.abstractFactory;

import factory.products.*;

/**
 * @author:李罡毛
 * @date:2021/2/13 15:05
 */
public class ModernFactory extends AbstractFactory {
    @Override
    Vehicle produceVehicle() {
        return new Car();
    }

    @Override
    Weapon produceWeapon() {
        return new Gun();
    }

    @Override
    Food produceFood() {
        return new Bread();
    }
}
