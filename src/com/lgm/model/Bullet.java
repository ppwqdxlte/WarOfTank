package com.lgm.model;

import com.lgm.enumeration.Dir;
import com.lgm.facade.GameModel;
import com.lgm.mgr.PropertiesMgr;
import com.lgm.mgr.ResourceMgr;

import java.awt.*;

/**
 * @author:李罡毛
 * @date:2021/2/5 13:18
 */
public class Bullet extends GameObject {

    private static final long serialVersionUID = 8918545667283636286L;
//    private int x;
//    private int y;
    private Dir dir;
    private final int SPEED = Integer.parseInt((String) PropertiesMgr.getProperty("bulletSpeed"));
    private boolean isLive = true;//子弹撞车或者跃出窗口就移除，等待回收，否则子弹变多后占用内存导致内存溢出
    private int fireX,fireY;//子弹射出时候的坐标，通过坦克坐标、图片长宽、运行方向 获取
    static int WIDTH = ResourceMgr.bulletL.getWidth(),HEIGHT = ResourceMgr.bulletU.getHeight();//子弹的宽度，高度
    private Tank tank;//所属坦克
    private Rectangle rectangle = new Rectangle(0,0,WIDTH,HEIGHT);//this子弹的矩形

    public Bullet(int x, int y, Dir dir, Tank tank) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tank = tank;
    }

    public void paint(Graphics g){

        this.move();

        switch (dir){
            case LEFT:
                fireX = x;
                fireY = y-HEIGHT/2+ ResourceMgr.tankL.getHeight()/2;
                g.drawImage(ResourceMgr.bulletL,fireX,fireY,null);
                break;
            case RIGHT:
                fireX = x+ ResourceMgr.tankR.getWidth()-WIDTH;
                fireY = y-HEIGHT/2+ ResourceMgr.tankR.getHeight()/2;
                g.drawImage(ResourceMgr.bulletR,fireX,fireY,null);
                break;
            case UP:
                fireX = x+ ResourceMgr.tankU.getWidth()/2-WIDTH/2;
                fireY = y;
                g.drawImage(ResourceMgr.bulletU,fireX,fireY,null);
                break;
            case DOWN:
                fireX = x+ ResourceMgr.tankD.getWidth()/2-WIDTH/2;
                fireY = y+ ResourceMgr.tankD.getHeight()-HEIGHT;
                g.drawImage(ResourceMgr.bulletD,fireX,fireY,null);
                break;
        }
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    private void move(){
        if (dir == Dir.LEFT){
            x -= SPEED;
        }else if (dir == Dir.RIGHT){
            x += SPEED;
        }else if (dir == Dir.UP){
            y -= SPEED;
        }else if (dir == Dir.DOWN){
            y += SPEED;
        }
        if (x<0||y<0||x> GameModel.getInstance().getTankFrame().getWidth()||y> GameModel.getInstance().getTankFrame().getHeight()){
            this.die();
        }
    }

    public static int getWIDTH(){
        return WIDTH;
    }
    public static int getHEIGHT(){
        return HEIGHT;
    }
    public Tank getTank(){
        return tank;
    }
    public Rectangle getRectangle() {
        return rectangle;
    }
    public void setLive(boolean live) {
        isLive = live;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void die() {
        this.setLive(false);
        GameModel.getInstance().getGameObjects().remove(this);
    }
}
