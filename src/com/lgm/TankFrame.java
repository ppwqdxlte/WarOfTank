package com.lgm;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

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

    public TankFrame() throws HeadlessException {
        this.tank = new Tank(40,30, IMMOBILE);
        this.setTitle("坦克大战");
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        this.setResizable(false);
        this.setVisible(true);
        //添加键盘监听器
        this.addKeyListener(new MyKeyAdapter());
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
        this.tank.paint(g);
        for (Bullet b:
             bullets) {
            if (b!=null) b.paint(g);
        }
    }

    class MyKeyAdapter extends KeyAdapter {

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
                Bullet bullet = new Bullet(tank.getX(),tank.getY(),shootingDir);
                bullets.add(bullet);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            tank.setDir(IMMOBILE);
        }
    }
}
