package factory.abstractFactory;

import factory.products.*;
/**
 * @author:李罡毛
 * @date:2021/2/13 15:08
 */
public class Test {
    public static void main(String[] args) {
        AbstractFactory af = new MagicFactory();
        Weapon weapon = af.produceWeapon();
        Food food = af.produceFood();
        Vehicle vehicle = af.produceVehicle();
        weapon.shoot();
        food.printName();
        vehicle.go();
    }
}
