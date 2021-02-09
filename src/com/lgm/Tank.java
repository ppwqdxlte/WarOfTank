package com.lgm;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author:李罡毛
 * @date:2021/2/5 10:24
 */
public class Tank implements Serializable {

    @Serial
    private static final long serialVersionUID = 4647626261280633755L;
    private int x;
    private int y;
    private Dir dir;
    private int SPEED = 5;
    private Dir dirBeforeImmobile;
    private TankFrame tankFrame;
    private static final int WIDTH = ResourceMgr.tankL.getWidth();
    private static final int HEIGHT = ResourceMgr.tankL.getHeight();
    private boolean isLive = true;//坦克存活状态，初始化是true
    private Group group;//区分敌方或友方坦克
    private final Random random = new Random();//随机数，控制子弹发射
    private final List<Bullet> bulletList = new ArrayList<Bullet>();//弹夹
    private int f;//第几帧，控制坦克按照一个方向的运行起止
    private int secRandom;//坦克按照一个方向的运行秒数的随机数
    private final Rectangle myRectangle = new Rectangle(x,y,WIDTH,HEIGHT);//this坦克的矩形
    private final Rectangle otherRectangle = new Rectangle(x,y,WIDTH,HEIGHT);//碰撞坦克的矩形
    private Rectangle interSection;//碰撞交叉区域
    private int escapeSpeed;//友军相会逃离速度

    public Tank() {
    }

    public Tank(int x, int y, Dir dir,TankFrame tankFrame,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
    }

    public Tank(int x, int y, Dir dir,TankFrame tankFrame,Group group,int speed) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
        this.SPEED = speed;
    }

    public List<Bullet> getBulletList() {
        return bulletList;
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
    public Dir getDirBeforeImmobile() {
        return dirBeforeImmobile;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Dir getDir() {
        return dir;
    }
    public int getSPEED() {
        return SPEED;
    }
    public void setDir(Dir dir) {
        this.dir = dir;
    }
    public void setDirBeforeImmobile(Dir dirBeforeImmobile) {
        this.dirBeforeImmobile = dirBeforeImmobile;
    }

    public void paint(Graphics g) {
        //如果是敌方，绘制前判断一下存活状态
        if (!isLive && this.getGroup() == Group.BAD){
            Explode explode = new Explode(x,y,tankFrame);
            tankFrame.getExplodeList().add(explode);
            tankFrame.getTanks().remove(this);
            return;
        }
      //静止前方向如果是null,初始化赋予向右的方向
        if (dirBeforeImmobile==null && this.group == Group.GOOD){
            dirBeforeImmobile = Dir.RIGHT;
            g.drawImage(ResourceMgr.tankR,x,y,null);
            return;
        }else if (dirBeforeImmobile == null && this.group ==Group.BAD){
            dirBeforeImmobile = Dir.DOWN;
            g.drawImage(ResourceMgr.badTankD,x,y,null);
            return;
        }
        //绘制子弹
        for (int i = 0; i < this.bulletList.size(); i++) {
            if (this.bulletList.get(i)!=null){
                this.bulletList.get(i).paint(g);
            }
        }
        //嵌套循环，子弹与敌方坦克的碰撞验证
        for (int i = 0; i < this.bulletList.size(); i++) {
            for (int j = 0; j < tankFrame.getTanks().size(); j++) {
                this.bulletList.get(i).collideWith(tankFrame.getTanks().get(j));
            }
        }
        //坦克碰撞验证
        for (int i = 0; i < tankFrame.getTanks().size(); i++) {
            if (this == tankFrame.getTanks().get(i))continue;
            this.collideWith(tankFrame.getTanks().get(i));
        }

        switch (dirBeforeImmobile){
            case LEFT:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.tankL:ResourceMgr.badTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.tankR:ResourceMgr.badTankR,x,y,null);
                break;
            case UP:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.tankU:ResourceMgr.badTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.tankD:ResourceMgr.badTankD,x,y,null);
                break;
        }
        this.move();
        this.tankRandomAction();
    }

    /**
     * @param tank 与其它坦克碰撞测试
     */
    private void collideWith(Tank tank) {
        myRectangle.setSize(WIDTH,HEIGHT);
        myRectangle.setLocation(x,y);
        otherRectangle.setSize(tank.getWIDTH(),tank.getHEIGHT());
        otherRectangle.setLocation(tank.getX(),tank.getY());
        //如果碰撞
        if (myRectangle.intersects(otherRectangle)) {
            //判断是否友军,是友军就不炸,且不会相交不会覆盖(改变坐标)
            if (this.getGroup() == tank.getGroup()){
                //玩家操作的主战坦克忽略
                if (this == tankFrame.getTank())return;
                //交叉区域矩形几何中心和当前坦克几何中心的相对位置，来决定当前坦克的位移
                interSection = myRectangle.intersection(otherRectangle);
                escapeSpeed = SPEED>=tank.SPEED?SPEED:tank.SPEED;
                if (Math.abs(interSection.getCenterX() - myRectangle.getCenterX())//左右移动
                        <=Math.abs(interSection.getCenterY() - myRectangle.getCenterY())) {
                    if (interSection.getCenterX() > myRectangle.getCenterX()) {//左移
                        x -= escapeSpeed;
                        dirBeforeImmobile = dir = Dir.LEFT;
                    }else {//右移
                        x += escapeSpeed;
                        dirBeforeImmobile = dir = Dir.RIGHT;
                    }
                }else {//上下移动
                    if (interSection.getCenterY() > myRectangle.getCenterY()){//上移
                        y -= escapeSpeed;
                        dirBeforeImmobile = dir = Dir.UP;
                    }else{//下移
                        y += escapeSpeed;
                        dirBeforeImmobile = dir = Dir.DOWN;
                    }
                }
                return;
            }
            this.isLive = false;
            tank.setIsLive(false);
        }
    }

    /**
     * 除了玩家操纵的主战坦克，其它坦克的随机行为
     */
    private void tankRandomAction() {
        if (this != tankFrame.getTank()){
            //坦克随机发射子弹
            if (random.nextInt(100)>95) {
                this.fire();
            }
            //坦克随机移动 代表几个方向 tempNum,f表示第几帧，控制改变方向,secRandom 随机秒数
            f++;
            if (f>=20*secRandom){//游戏程序入口50毫秒刷新一帧，1秒20帧左右，这里设置敌坦克按照一个方向的运行时间单位为1秒，具体几秒由随机数获得
                secRandom = random.nextInt(10)+1;//确定下一次的时长，保底1秒
                f = 0;//f清零
                //确定下一次的方向
                this.dirBeforeImmobile = dir = Dir.values()[random.nextInt(4)];
            }
        }
    }

    /**
     * 坦克移动（改变坐标值），但是不能出界，必须在窗体内运行
     */
    private void move(){
        if (dir == Dir.LEFT){
            x = x-SPEED<=2?x:x-SPEED;
        }else if (dir == Dir.RIGHT){
            x = x+SPEED>=tankFrame.getWidth()-WIDTH-2?x:x+SPEED;
        }else if (dir == Dir.UP){
            y = y-SPEED<=28?y:y-SPEED;
        }else if (dir == Dir.DOWN){
            y = y+SPEED>=tankFrame.getHeight()-HEIGHT-28?y:y+SPEED;
        }
    }

    /**
     * 开火
     */
    public void fire() {
        Bullet bullet = new Bullet(this.x,this.y,this.dirBeforeImmobile,this.tankFrame,this);
        bulletList.add(bullet);
    }
}
