package com.lgm.strategy;

import com.lgm.enumeration.Dir;
import com.lgm.enumeration.Group;
import com.lgm.model.Bullet;
import com.lgm.model.Tank;
import com.lgm.util.Audio;

/**
 * @author:李罡毛
 * @date:2021/2/12 18:40
 */
public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + tank.getWIDTH()/2 - Bullet.getWIDTH()/2;
        int bY = tank.getY() + tank.getHEIGHT()/2 - Bullet.getHEIGHT()/2;

        Dir[] dirs = Dir.values();
        for(Dir dir : dirs) {
            Bullet bullet = new Bullet(bX, bY, dir, tank);
            tank.getGameModel().getGameObjects().add(bullet);
        }

        if(tank.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
