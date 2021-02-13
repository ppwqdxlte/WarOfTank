package com.lgm;

/**
 * @author:李罡毛
 * @date:2021/2/12 18:40
 */
public class FourDirFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank t) {
        int bX = t.getX() + t.getWIDTH()/2 - Bullet.WIDTH/2;
        int bY = t.getY() + t.getHEIGHT()/2 - Bullet.HEIGHT/2;

        Dir[] dirs = Dir.values();
        for(Dir dir : dirs) {
            if (dir != Dir.IMMOBILE) new Bullet(bX, bY, dir, t.getTankFrame(), t);
        }

        if(t.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
