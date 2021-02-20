package com.lgm.facade;

import com.lgm.collider.Collider;
import com.lgm.collider.ColliderChain;
import com.lgm.enumeration.Dir;
import com.lgm.enumeration.Group;
import com.lgm.mgr.PropertiesMgr;
import com.lgm.model.*;
import com.lgm.view.TankFrame;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static com.lgm.enumeration.Dir.RIGHT;

/**
 * @author:李罡毛
 * @date:2021/2/17 15:50
 */
public class GameModel {

    private TankFrame tankFrame;
    private List<GameObject> gameObjects = new ArrayList<>();
    private ColliderChain colliderChain = new ColliderChain();
    private static GameModel instance;

    private GameModel(){}

    private GameModel(TankFrame tankFrame){

        //添加主坦克
        Tank mainTank = new Tank(Integer.parseInt((String)PropertiesMgr.getProperty("myTankInitX")),
                Integer.parseInt((String)PropertiesMgr.getProperty("myTankInitY")),
                RIGHT,Group.GOOD,
                Integer.parseInt((String)PropertiesMgr.getProperty("myTankSpeed")));
        mainTank.setIsMoving(false);
        gameObjects.add(mainTank);//gameObjects[0] == mainTank

        //初始化电脑坦克
        int initTankCount = Integer.parseInt((String) PropertiesMgr.getProperty("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            gameObjects.add(new Tank(300+i*50,10, Dir.DOWN, Group.BAD,5));
            if (i%5 == 0)
            gameObjects.add(new Tank(100+i*50,300,Dir.UP,Group.GOOD,5));
        }
        this.tankFrame = tankFrame;

        //添加墙体
        gameObjects.add(new Wall(100,100,200,50));
        gameObjects.add(new Wall(600,100,200,50));
        gameObjects.add(new Wall(300,300,50,200));
        gameObjects.add(new Wall(700,300,50,200));

        //碰撞责任链从配置文件中读取列表，根据配置添加责任对象
        String[] colliders = ((String) PropertiesMgr.getProperty("colliderChain")).split(",");
        for (int i = 0; i < colliders.length; i++) {
            try {
                colliderChain.add((Collider)Class.forName(colliders[i]).getDeclaredConstructor().newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static GameModel getInstance() {
        return instance;
    }

    public static void init(TankFrame tankFrame) {
        if (instance == null){
            synchronized (GameModel.class){
                if (instance == null){
                    instance = new GameModel(tankFrame);
                }
            }
        }
    }

    public TankFrame getTankFrame() {
        return tankFrame;
    }
    public List<GameObject> getGameObjects(){
        return gameObjects;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.MAGENTA);
        g.drawString("我的坦克子弹的数量："+gameObjects.stream().filter((x)->x instanceof Bullet && ((Bullet)x).getTank() == (Tank)(gameObjects.get(0))).count(),10,50);
        g.drawString("敌方坦克的数量："+gameObjects.stream().filter((x)->x instanceof Tank && ((Tank)x).getGroup()==Group.BAD).count(),10,70);
        g.drawString("爆炸对象数量："+gameObjects.stream().filter((x)->x instanceof Explode).count(),10,90);
        g.drawString("友军坦克数量（除了我自己）:"+(gameObjects.stream().filter(
                (x)->(x instanceof Tank && ((Tank)x).getGroup()==Group.GOOD)).count()-1),10,110);
        g.setColor(c);

        //碰撞测试
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i+1; j < gameObjects.size(); j++) {
                colliderChain.collideBetween(gameObjects.get(i),gameObjects.get(j));
            }
        }

        //绘制模型
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }
    }

}
