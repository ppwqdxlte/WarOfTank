package com.lgm.collider;

import com.lgm.model.Bullet;
import com.lgm.model.GameObject;
import com.lgm.model.Wall;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:李罡毛
 * @date:2021/2/19 21:01
 */
public class BulletWallCollider implements Collider<Bullet, Wall> {
    private AtomicInteger counter = new AtomicInteger(0);
    @Override
    public boolean collideBetween(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall){
            //必须更新位置
            setRectangleLocation((Bullet) o1,(Wall) o2);
            if (((Bullet) o1).getRectangle().intersects(((Wall) o2).getRectangle())){
//                System.err.println("子弹"+o1.hashCode()+"打在了墙"+o2.hashCode()+"上！"+(counter.incrementAndGet()));
                ((Bullet) o1).die();
                return false;
            }
//            System.out.println("Bullet:\t"+o1.hashCode()+"\tWall:\t"+o2.hashCode());
        }else if (o1 instanceof Wall && o2 instanceof Bullet){
            return collideBetween(o2,o1);
        }
        return true;
    }

    @Override
    public void setRectangleLocation(Bullet bullet, Wall wall) {
        bullet.getRectangle().setLocation(bullet.getX(),bullet.getY());
    }
}
