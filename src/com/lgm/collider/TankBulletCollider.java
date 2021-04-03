package com.lgm.collider;

import com.lgm.model.Bullet;
import com.lgm.model.GameObject;
import com.lgm.model.Tank;

import java.util.UUID;

/**
 * @author:李罡毛
 * @date:2021/2/18 19:46
 */
public class TankBulletCollider implements Collider<Bullet, Tank> {

    @Override
    public boolean collideBetween(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank){
            //判断是否友军,是友军就不炸
            Tank tank = null;
            UUID tankId =((Bullet) o1).getTankId();
            GameObject gameObject = ((Bullet) o1).getGameModel().getGameObjectWithUUID(tankId);
            tank = (Tank) gameObject;
            if (tank.getGroup() == ((Tank) o2).getGroup()) return false;
            //设置矩形位置
            setRectangleLocation((Bullet) o1,(Tank) o2);
            //自己的子弹炸不死自己
            if (!tank.getUuid().toString().equals(((Tank)o2).getUuid().toString())
                    && ((Bullet) o1).getRectangle().intersects(((Tank) o2).getRectangle())) {
                ((Bullet) o1).die();
                ((Tank) o2).die();
                return false;
            }
        }else if (o1 instanceof Tank && o2 instanceof Bullet){
            return collideBetween(o2,o1);
        }
        return true;
    }

    @Override
    public void setRectangleLocation(Bullet bullet, Tank tank) {
        bullet.getRectangle().setLocation(bullet.getX(),bullet.getY());
        tank.getRectangle().setLocation(tank.getX(),tank.getY());
    }

}
