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

    private final Tank tank ;
    private final List<Bullet> bullets = new ArrayList<>();//弹夹
    private Dir shootingDir = RIGHT;//子弹射出方向
    private static final int GAME_WIDTH = 800,GAME_HEIGHT = 600;

    public int getWidth(){
        return GAME_WIDTH;
    }
    public int getHeight(){
        return GAME_HEIGHT;
    }
    public List<Bullet> getBullets(){
        return bullets;
    }
    public TankFrame() throws HeadlessException {
        this.tank = new Tank(40,30, IMMOBILE);
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
        g.drawString("子弹的数量："+bullets.size(),10,60);
        g.setColor(c);
        this.tank.paint(g);
        /*
        //这里有一个大坑，foreach底层用iterator迭代，而iterator迭代过程中不能执行remove()方法，否则出错，
        //而传统的fori方法就可以边迭代边删除而不报错！
        for (Bullet b:
             bullets) {
            if (b!=null) b.paint(g);
        }*/
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i)!=null){
                bullets.get(i).paint(g);
            }
        }
    }

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
            }else if (keyCode == KeyEvent.VK_RIGHT){
                tank.setDir(RIGHT);
                shootingDir = RIGHT;
            }else if (keyCode == KeyEvent.VK_UP){
                tank.setDir(UP);
                shootingDir = UP;
            }else if (keyCode == KeyEvent.VK_DOWN){
                tank.setDir(DOWN);
                shootingDir = DOWN;
            }else {
                tank.setDir(IMMOBILE);
            }
//            repaint();
            //判断子弹方向
            if (keyCode == KeyEvent.VK_CONTROL){
                Bullet bullet = new Bullet(tank.getX(),tank.getY(),shootingDir,this.tankFrame);
                bullets.add(bullet);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            tank.setDir(IMMOBILE);
        }

    }
}
