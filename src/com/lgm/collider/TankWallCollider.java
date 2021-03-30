package com.lgm.collider;

import com.lgm.enumeration.Dir;
import com.lgm.model.GameObject;
import com.lgm.model.Tank;
import com.lgm.model.Wall;

import java.awt.*;

/**
 * @author:李罡毛
 * @date:2021/2/19 20:22
 */
public class TankWallCollider implements Collider<Tank, Wall> {
    @Override
    public boolean collideBetween(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall){
            setRectangleLocation((Tank) o1,(Wall) o2 );
            if (((Tank) o1).getRectangle().intersects(((Wall) o2).getRectangle())){

                //交叉区域矩形几何中心和当前坦克几何中心的相对位置，来决定当前坦克的位移
                Rectangle interSection = ((Tank) o1).getRectangle().intersection(((Wall) o2).getRectangle());
                int escapeSpeed = ((Tank) o1).getSPEED();
                if (Math.abs(interSection.getCenterX() - ((Tank) o1).getRectangle().getCenterX())//左右移动
                        <=Math.abs(interSection.getCenterY() - ((Tank) o1).getRectangle().getCenterY())) {
                    if (interSection.getCenterX() > ((Tank) o1).getRectangle().getCenterX()) {//左移
                        ((Tank) o1).setX(((Tank) o1).getX()-escapeSpeed);
                        ((Tank) o1).setDir(Dir.LEFT);
                    }else {//右移
                        ((Tank) o1).setX(((Tank) o1).getX()+escapeSpeed);
                        ((Tank) o1).setDir(Dir.RIGHT);
                    }
                }else {//上下移动
                    if (interSection.getCenterY() > ((Tank) o1).getRectangle().getCenterY()){//上移
                        ((Tank) o1).setY(((Tank) o1).getY()-escapeSpeed);
                        ((Tank) o1).setDir(Dir.UP);
                    }else{//下移
                        ((Tank) o1).setY(((Tank) o1).getY()+escapeSpeed);
                        ((Tank) o1).setDir(Dir.DOWN);
                    }
                }
                return false;
            }
        }else if (o1 instanceof Wall && o2 instanceof Tank){
            return collideBetween(o2,o1);
        }
        return true;
    }

    @Override
    public void setRectangleLocation(Tank tank, Wall wall) {
        tank.getRectangle().setLocation(tank.getX(),tank.getY());
    }
}
