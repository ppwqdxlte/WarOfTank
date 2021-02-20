package com.lgm.collider;

import com.lgm.enumeration.Dir;
import com.lgm.enumeration.Group;
import com.lgm.model.GameObject;
import com.lgm.model.Tank;

import java.awt.*;

/**
 * @author:李罡毛
 * @date:2021/2/18 19:49
 */
public class TankTankCollider implements Collider<Tank,Tank>{
    @Override
    public boolean collideBetween(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank){
            //如果碰撞
            Rectangle interSection = null;
            int escapeSpeed = 0;
            //设置矩形位置
            setRectangleLocation((Tank) o1,(Tank) o2);
            if (((Tank) o1).getRectangle().intersects(((Tank) o2).getRectangle())) {
                //判断是否友军,是友军就不炸,且不会相交不会覆盖(改变坐标)
                if (((Tank) o1).getGroup() == ((Tank) o2).getGroup()){
                    //交叉区域矩形几何中心和当前坦克几何中心的相对位置，来决定当前坦克的位移
                    interSection = ((Tank) o1).getRectangle().intersection(((Tank) o2).getRectangle());
                    escapeSpeed = ((Tank) o1).getSPEED()>=((Tank) o2).getSPEED()?((Tank) o1).getSPEED():((Tank) o2).getSPEED();
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
                }else {
                    //碰撞双方不是友军
                    if (((Tank) o1).getGroup() == Group.BAD) ((Tank) o1).die();
                    if (((Tank) o2).getGroup() == Group.BAD) ((Tank) o2).die();
                }
                return false;
            }
            return true;
        }
        return true;
    }

    @Override
    public void setRectangleLocation(Tank tank, Tank tank2) {
        tank.getRectangle().setLocation(tank.getX(),tank.getY());
        tank2.getRectangle().setLocation(tank2.getX(),tank2.getY());
    }

}