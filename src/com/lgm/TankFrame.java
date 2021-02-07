package com.lgm;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import static com.lgm.Dir.*;

/**
 * @author:李罡毛
 * @date:2021/2/4 17:17
 */
public class TankFrame extends Frame {

    private final Tank tank ;//我方坦克
    private Dir shootingDir = RIGHT;//子弹射出方向
    private static final int GAME_WIDTH = 800,GAME_HEIGHT = 600;//窗口大小
    private final List<Tank> tanks = new ArrayList<>();//全部坦克集合

    public List<Tank> getTanks(){
        return tanks;
    }
    public Tank getTank() {
        return tank;
    }
    public int getWidth(){
        return GAME_WIDTH;
    }
    public int getHeight(){
        return GAME_HEIGHT;
    }
    public TankFrame() throws HeadlessException {
        this.tank = new Tank(40,300, IMMOBILE,this,Group.GOOD,15);
        this.tanks.add(tank);
        this.setTitle("坦克大战");
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        this.setResizable(false);
        this.setVisible(true);
        //添加键盘监听器
        this.addKeyListener(new MyKeyAdapter(this));
        //添加窗口监听器
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private Image offScreenImage = null;
    /**
     * 界面防抖
     * @param g
     * offScreenImage:
     */
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GREEN);
        g.drawString("我的坦克子弹的数量："+this.tank.getBulletList().size(),10,60);
        g.drawString("敌方坦克的数量："+tanks.stream().filter((x)->x.getGroup()==Group.BAD).count(),10,90);
        g.setColor(c);
        //绘制坦克
        for (int i = 0; i < this.tanks.size(); i++) {
            this.tanks.get(i).paint(g);
        }
    }

    /**
     * tank:指的是我用键盘控制的坦克，不是敌方坦克
     */
    class MyKeyAdapter extends KeyAdapter {
        private final TankFrame tankFrame;

        public MyKeyAdapter(TankFrame tankFrame) {
            super();
            this.tankFrame = tankFrame;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            //判断坦克方向
            if (keyCode == KeyEvent.VK_LEFT){
                tank.setDir(LEFT);
                shootingDir = LEFT;
                tankFrame.getTank().setDirBeforeImmobile(LEFT);
            }else if (keyCode == KeyEvent.VK_RIGHT){
                tank.setDir(RIGHT);
                shootingDir = RIGHT;
                tankFrame.getTank().setDirBeforeImmobile(RIGHT);
            }else if (keyCode == KeyEvent.VK_UP){
                tank.setDir(UP);
                shootingDir = UP;
                tankFrame.getTank().setDirBeforeImmobile(UP);
            }else if (keyCode == KeyEvent.VK_DOWN){
                tank.setDir(DOWN);
                shootingDir = DOWN;
                tankFrame.getTank().setDirBeforeImmobile(DOWN);
            }else {
                tank.setDir(IMMOBILE);
            }
//            repaint();
            //判断子弹方向
            if (keyCode == KeyEvent.VK_CONTROL){
                Bullet bullet = new Bullet(tank.getX(),tank.getY(),shootingDir,this.tankFrame,tank);
                tank.getBulletList().add(bullet);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            tank.setDir(IMMOBILE);
        }

    }
}
