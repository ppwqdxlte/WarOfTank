package com.lgm;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.lgm.Dir.RIGHT;

/**
 * @author:李罡毛
 * @date:2021/2/17 15:50
 */
public class GameModel {

    private TankFrame tankFrame;
    private Tank mainTank ;//主坦克
    private final List<Tank> tanks = new ArrayList<>();//全部坦克集合
    private final List<Explode> explodes = new ArrayList<>();//爆炸集合

    public GameModel(){}
    public GameModel(TankFrame tankFrame){
        int initTankCount = Integer.parseInt((String)PropertiesMgr.getProperty("initTankCount"));
        //初始化电脑坦克
        for (int i = 0; i < initTankCount; i++) {
            this.tanks.add(new Tank(300+i*50,10,Dir.DOWN,Group.BAD,5,this));
            if (i%5 == 0)
            this.tanks.add(new Tank(100+i*50,300,Dir.UP,Group.GOOD,5,this));
        }
        this.tankFrame = tankFrame;
        this.mainTank = new Tank(Integer.parseInt((String)PropertiesMgr.getProperty("myTankInitX")),
                Integer.parseInt((String)PropertiesMgr.getProperty("myTankInitY")),
                RIGHT,Group.GOOD,
                Integer.parseInt((String)PropertiesMgr.getProperty("myTankSpeed")),this);
        this.mainTank.setIsMoving(false);
        this.tanks.add(mainTank);
    }
    public List<Explode> getExplodes(){ return explodes;}
    public List<Tank> getTanks(){
        return tanks;
    }
    public Tank getTank() {
        return mainTank;
    }
    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.MAGENTA);
        g.drawString("我的坦克子弹的数量："+this.mainTank.getBulletList().size(),10,50);
        g.drawString("敌方坦克的数量："+tanks.stream().filter((x)->x.getGroup()==Group.BAD).count(),10,70);
        g.drawString("爆炸对象数量："+this.explodes.size(),10,90);
        g.drawString("友军坦克数量（除了我自己）:"+tanks.stream().filter(
                (x)->x.getGroup()==Group.GOOD&&x!=this.mainTank).count(),10,110);
        g.setColor(c);
        //绘制坦克
        for (int i = 0; i < this.tanks.size(); i++) {
            this.tanks.get(i).paint(g);
        }
        //绘制爆炸
        for (int i = 0; i < explodes.size(); i++) {
            this.explodes.get(i).paint(g);
        }
    }

}
