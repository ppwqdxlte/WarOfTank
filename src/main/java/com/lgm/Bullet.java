package com.lgm;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author:李罡毛
 * @date:2021/2/5 13:18
 */
public class Bullet implements Serializable {
    @Serial
    private static final long serialVersionUID = 8918545667283636286L;
    private int x;
    private int y;
    private Dir dir;
    private final int SPEED = Integer.parseInt((String)PropertiesMgr.getProperty("bulletSpeed"));
    private boolean isLive = true;//子弹撞车或者跃出窗口就移除，等待回收，否则子弹变多后占用内存导致内存溢出
    private TankFrame tankFrame;//获取坦克窗口的私有属性
    private int fireX,fireY;//子弹射出时候的坐标，通过坦克坐标、图片长宽、运行方向 获取
    static int WIDTH = ResourceMgr.bulletL.getWidth(),HEIGHT = ResourceMgr.bulletU.getHeight();//子弹的宽度，高度
    private Tank tank;//所属坦克
    private final Rectangle myRectangle = new Rectangle(x,y,WIDTH,HEIGHT);//this子弹的矩形
    private final Rectangle otherRectangle = new Rectangle(x,y,WIDTH,HEIGHT);//碰撞坦克的矩形

    public Bullet() {
    }

    public Bullet(int x, int y, Dir dir,TankFrame tankFrame,Tank tank) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.tank = tank;
        tank.getBulletList().add(this);
    }

    public void paint(Graphics g){
        if (!isLive){
            this.tank.getBulletList().remove(this);
            return;
        }

        switch (dir){
            case LEFT:
                fireX = x;
                fireY = y-HEIGHT/2+ResourceMgr.tankL.getHeight()/2;
                g.drawImage(ResourceMgr.bulletL,fireX,fireY,null);
                break;
            case RIGHT:
                fireX = x+ResourceMgr.tankR.getWidth()-WIDTH;
                fireY = y-HEIGHT/2+ResourceMgr.tankR.getHeight()/2;
                g.drawImage(ResourceMgr.bulletR,fireX,fireY,null);
                break;
            case UP:
                fireX = x+ResourceMgr.tankU.getWidth()/2-WIDTH/2;
                fireY = y;
                g.drawImage(ResourceMgr.bulletU,fireX,fireY,null);
                break;
            case DOWN:
                fireX = x+ResourceMgr.tankD.getWidth()/2-WIDTH/2;
                fireY = y+ResourceMgr.tankD.getHeight()-HEIGHT;
                g.drawImage(ResourceMgr.bulletD,fireX,fireY,null);
                break;
        }
        this.move();
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
        if (x<0||y<0||x>tankFrame.getWidth()||y> tankFrame.getHeight()){
            this.isLive = false;
        }
    }
    //子弹与坦克碰撞
    public void collideWith(Tank tank) {
        //判断是否友军,是友军就不炸
        if (this.tank.getGroup() == tank.getGroup()){
            return;
        }
        myRectangle.setSize(WIDTH,HEIGHT);
        myRectangle.setLocation(x,y);
        otherRectangle.setSize(tank.getWIDTH(),tank.getHEIGHT());
        otherRectangle.setLocation(tank.getX(),tank.getY());
        //不会误伤友军，只会炸掉敌方坦克
        if (myRectangle.intersects(otherRectangle)) {
            this.isLive = false;
            tank.setIsLive(false);
        }
    }

}
