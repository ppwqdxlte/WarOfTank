package com.lgm.facade;

import com.lgm.collider.Collider;
import com.lgm.collider.ColliderChain;
import com.lgm.enumeration.Dir;
import com.lgm.enumeration.Group;
import com.lgm.mgr.PropertiesMgr;
import com.lgm.model.*;
import com.lgm.net.Client;
import com.lgm.net.TankJoinMsg;
import com.lgm.view.TankFrame;

import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;

/**
 * @author:李罡毛
 * @date:2021/2/17 15:50
 */
public class GameModel implements Serializable{

    private TankFrame tankFrame;
    private List<GameObject> gameObjects = new ArrayList<>();
    private ColliderChain colliderChain = new ColliderChain();
    private static GameModel instance;
    private Random random = new Random();
    private Client client;
    private Map<UUID,GameObject> goMap = new Hashtable<>();

    private GameModel(){}

    public GameModel(TankFrame tankFrame){
        this.tankFrame = tankFrame;
        //添加主坦克，初始位置随机
        Tank mainTank = new Tank(random.nextInt(tankFrame.getWidth()),random.nextInt(tankFrame.getHeight()),
                Dir.RIGHT, Group.GOOD,
                Integer.parseInt((String) PropertiesMgr.getProperty("myTankSpeed")),this);
        mainTank.setIsMoving(false);
        gameObjects.add(mainTank);//gameObjects[0] == mainTank
        goMap.put(mainTank.getUuid(),mainTank);

        //初始化电脑坦克
        /*int initTankCount = Integer.parseInt((String) PropertiesMgr.getProperty("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            Tank tb = new Tank(300+i*50,10, Dir.DOWN, Group.BAD,5,this);
            gameObjects.add(tb);
            goMap.put(tb.getUuid(),tb);
            if (i%5 == 0){
                Tank tg = new Tank(100+i*50,300, Dir.UP, Group.GOOD,5,this);
                gameObjects.add(tg);
                goMap.put(tg.getUuid(),tg);
            }
        }*/

        //添加墙体
        gameObjects.add(new Wall(100,120,200,50));
        gameObjects.add(new Wall(600,120,200,50));
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

        //连接到服务器，必须启动新线程，因为client连接后会阻塞住，放在一起就无法初始化TankFrame
        client = new Client(this);
        new Thread(()->{
            try {
                Thread.sleep(100);//阻塞一会儿以确保main-tank和client成功初始化
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            client.connect();
        }).start();

        //发送tankJoinMsg给server
        TankJoinMsg tankJoinMsg = new TankJoinMsg(mainTank.getX(),mainTank.getY(),mainTank.getDir()
                ,mainTank.getIsMoving(),mainTank.getGroup(),mainTank.getUuid());
        if (client.getChannel()!=null && client.getChannel().isActive()) {
            client.getChannel().writeAndFlush(tankJoinMsg);
        } else {
            while (client.getChannel() == null){ } //空轮询，等待channel初始化
            while (!client.getChannel().isActive()){ } //空轮询，等待channel可用
            client.getChannel().writeAndFlush(tankJoinMsg);
        }
    }

    public TankFrame getTankFrame(){
        return tankFrame;
    }
    public List<GameObject> getGameObjects(){
        return gameObjects;
    }
    public Map<UUID,GameObject> getGoMap(){
        return goMap;
    }
    public GameObject getGameObjectWithUUID(UUID uuid) {
        return goMap.get(uuid);
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.MAGENTA);
        g.drawString("我的坦克子弹的数量："+gameObjects.stream().filter((x)->x instanceof Bullet && ((Bullet)x).getTank() == (Tank)(gameObjects.get(0))).count(),10,50);
        g.drawString("敌方坦克的数量："+gameObjects.stream().filter((x)->x instanceof Tank && ((Tank)x).getGroup()== Group.BAD).count(),10,70);
        g.drawString("爆炸对象数量："+gameObjects.stream().filter((x)->x instanceof Explode).count(),10,90);
        g.drawString("友军坦克数量（除了我自己）:"+(gameObjects.stream().filter(
                (x)->(x instanceof Tank && ((Tank)x).getGroup()== Group.GOOD)).count()-1),10,110);
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

    /**
     * 存盘
     */
    public void save() {
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        File file = new File("temp.data");
        try{
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 加载
     */
    public void load() {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        File file = new File("temp.data");
        try{
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            if (o instanceof GameModel) {
                instance = (GameModel) o;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
