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
    //窗口View与业务模型modle分离，中间用facade门面联系
    private GameModel gameModel;

    private static final int GAME_WIDTH = Integer.parseInt((String)PropertiesMgr.getProperty("gameWidth"));
    private static final int GAME_HEIGHT = Integer.parseInt((String)PropertiesMgr.getProperty("gameHeight"));

    public int getWidth(){
        return GAME_WIDTH;
    }
    public int getHeight(){
        return GAME_HEIGHT;
    }

    public TankFrame() throws HeadlessException {
        //初始化gameModle
        this.gameModel = new GameModel(this);
        this.setTitle((String)PropertiesMgr.getProperty("gameTitle"));
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
        gameModel.paint(g);
    }

    /**
     * tank:指的是我用键盘控制的坦克，不是敌方坦克
     */
    private class MyKeyAdapter extends KeyAdapter {
        private final TankFrame tankFrame;

        public MyKeyAdapter(TankFrame tankFrame) {
            super();
            this.tankFrame = tankFrame;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            gameModel.getTank().setIsMoving(true);
            //判断坦克方向
            if (keyCode == KeyEvent.VK_LEFT){
                gameModel.getTank().setDir(LEFT);
            }else if (keyCode == KeyEvent.VK_RIGHT){
                gameModel.getTank().setDir(RIGHT);
            }else if (keyCode == KeyEvent.VK_UP){
                gameModel.getTank().setDir(UP);
            }else if (keyCode == KeyEvent.VK_DOWN){
                gameModel.getTank().setDir(DOWN);
            }else {
                gameModel.getTank().setIsMoving(false);
            }
            //按ctrl键开火
            if (keyCode == KeyEvent.VK_CONTROL){
                gameModel.getTank().getFireStrategy().fire(gameModel.getTank());
            }
            if (gameModel.getTank().getIsMoving() == true)
            new Thread(()->new Audio("audio/tank_move.wav").play()).start();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            gameModel.getTank().setIsMoving(false);
        }

    }
}
