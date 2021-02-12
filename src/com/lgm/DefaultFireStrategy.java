package com.lgm;

/**
 * @author:李罡毛
 * @date:2021/2/12 18:40
 */
public class DefaultFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + tank.getWIDTH()/2 - Bullet.WIDTH/2;
        int bY = tank.getY() + tank.getHEIGHT()/2 - Bullet.HEIGHT/2;

        new Bullet(bX, bY, tank.getDirBeforeImmobile(), tank.getTankFrame(), tank);

        if(tank.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
