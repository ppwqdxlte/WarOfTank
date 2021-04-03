package com.lgm.strategy;

import com.lgm.enumeration.Dir;
import com.lgm.enumeration.Group;
import com.lgm.facade.GameModel;
import com.lgm.model.Bullet;
import com.lgm.model.Tank;
import com.lgm.net.BulletNewMsg;
import com.lgm.util.Audio;

/**
 * @author:李罡毛
 * @date:2021/2/12 18:40
 */
public class FourDirFireStrategy implements FireStrategy {
    private GameModel gameModel;
    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + tank.getWIDTH()/2 - Bullet.getWIDTH()/2;
        int bY = tank.getY() + tank.getHEIGHT()/2 - Bullet.getHEIGHT()/2;

        Dir[] dirs = Dir.values();
        for(Dir dir : dirs) {
            Bullet bullet = new Bullet(bX, bY, dir, tank.getUuid(),tank.getGameModel());
            this.gameModel.getGameObjects().add(bullet);
            this.gameModel.getGoMap().put(bullet.getUuid(),bullet);
            BulletNewMsg bulletNewMsg = new BulletNewMsg(bullet);
            tank.getGameModel().getClient().send(bulletNewMsg);
        }
    }
    @Override
    public void setGameModel(GameModel gameModel){
        this.gameModel = gameModel;
    }
}
