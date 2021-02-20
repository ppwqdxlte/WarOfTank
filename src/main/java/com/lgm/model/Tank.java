package com.lgm.model;

import com.lgm.facade.GameModel;
import com.lgm.enumeration.Dir;
import com.lgm.enumeration.Group;
import com.lgm.mgr.PropertiesMgr;
import com.lgm.mgr.ResourceMgr;
import com.lgm.strategy.*;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Random;

/**
 * @author:李罡毛
 * @date:2021/2/5 10:24
 */
public class Tank extends GameObject implements Serializable {

    @Serial
    private static final long serialVersionUID = 4647626261280633755L;
    private int x;
    private int y;
    private Dir dir;
    private Group group;
    private int SPEED = this.group==Group.GOOD?Integer.parseInt((String) PropertiesMgr.getProperty("goodTankSpeed")):Integer.parseInt((String)PropertiesMgr.getProperty("badTankSpeed"));
    private static final int WIDTH = ResourceMgr.tankL.getWidth();
    private static final int HEIGHT = ResourceMgr.tankL.getHeight();
    private boolean isLive = true;
    private boolean isMoving = true;
    private final Random random = new Random();//随机数，控制子弹发射
    private int f;//第几帧，控制坦克按照一个方向的运行起止
    private int secRandom;//坦克按照一个方向的运行秒数的随机数
    private Rectangle rectangle = new Rectangle(0,0,WIDTH,HEIGHT);//this坦克的矩形
    private FireStrategy fireStrategy;//开火策略

    public Tank(int x, int y, Dir dir, Group group, int speed) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.SPEED = speed;
        if(group == Group.GOOD) {
            String goodFSName = (String)PropertiesMgr.getProperty("goodFS");
            try {
                fireStrategy = (FireStrategy)Class.forName(goodFSName).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            fireStrategy = new DefaultFireStrategy();
        }

    }

    public void paint(Graphics g) {

        this.move();

        this.tankRandomAction();
        switch (dir){
            case LEFT:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.tankL:ResourceMgr.badTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.tankR:ResourceMgr.badTankR,x,y,null);
                break;
            case UP:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.tankU:ResourceMgr.badTankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.tankD:ResourceMgr.badTankD,x,y,null);
                break;
        }
    }


    /**
     * 除了玩家操纵的主战坦克，其它坦克的随机行为
     */
    private void tankRandomAction() {
        if (this != ((Tank)(GameModel.getInstance().getGameObjects().get(0)))){
            //坦克随机发射子弹
            if (random.nextInt(100)>97) {
                this.fire();
            }
            //坦克随机移动 代表几个方向 tempNum,f表示第几帧，控制改变方向,secRandom 随机秒数
            f++;
            if (f>=20*secRandom){//游戏程序入口50毫秒刷新一帧，1秒20帧左右，这里设置敌坦克按照一个方向的运行时间单位为1秒，具体几秒由随机数获得
                secRandom = random.nextInt(10)+1;//确定下一次的时长，保底1秒
                f = 0;//f清零
                //确定下一次的方向
                dir = Dir.values()[random.nextInt(4)];
            }
        }
    }

    /**
     * 坦克移动（改变坐标值），但是不能出界，必须在窗体内运行
     */
    private void move(){
        if (isMoving == false) return;
        if (dir == Dir.LEFT){
            x = x-SPEED<=2?x:x-SPEED;
        }else if (dir == Dir.RIGHT){
            x = x+SPEED>=GameModel.getInstance().getTankFrame().getWidth()-WIDTH-2?x:x+SPEED;
        }else if (dir == Dir.UP){
            y = y-SPEED<=28?y:y-SPEED;
        }else if (dir == Dir.DOWN){
            y = y+SPEED>=GameModel.getInstance().getTankFrame().getHeight()-HEIGHT-28?y:y+SPEED;
        }
    }

    /**
     * 开火，new子弹
     */
    public void fire() {
        this.fireStrategy.fire(this);
    }

    public FireStrategy getFireStrategy() {
        return fireStrategy;
    }
    public Group getGroup() {
        return group;
    }
    public int getWIDTH() {
        return WIDTH;
    }
    public int getHEIGHT() {
        return HEIGHT;
    }
    public void setIsLive(Boolean isLive) {
        this.isLive = isLive;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setDir(Dir dir) {
        this.dir = dir;
    }
    public void setIsMoving(boolean b) {
        this.isMoving = b;
    }
    public boolean getIsMoving() {
        return isMoving;
    }
    public boolean getIsLive(){
        return isLive;
    }
    public Dir getDir() {
        return dir;
    }
    public Rectangle getRectangle(){
        return rectangle;
    }
    public int getSPEED() {
        return SPEED;
    }

    public void die() {
        this.setIsLive(false);
        GameModel.getInstance().getGameObjects().remove(this);
        Explode explode = new Explode(x,y);
        GameModel.getInstance().getGameObjects().add(explode);
    }
}
