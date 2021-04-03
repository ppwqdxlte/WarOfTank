package com.lgm.view;

import com.lgm.enumeration.Dir;
import com.lgm.facade.GameModel;
import com.lgm.mgr.PropertiesMgr;
import com.lgm.model.Tank;
import com.lgm.net.TankDirChangedMsg;
import com.lgm.net.TankStartMovingMsg;
import com.lgm.net.TankStopMovingMsg;
import com.lgm.util.Audio;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

/**
 * @author:李罡毛
 * @date:2021/2/4 17:17
 */
public class TankFrame extends Frame implements Serializable {

    private GameModel gameModel;

    private static final int GAME_WIDTH = Integer.parseInt((String) PropertiesMgr.getProperty("gameWidth"));
    private static final int GAME_HEIGHT = Integer.parseInt((String) PropertiesMgr.getProperty("gameHeight"));

    public int getWidth(){
        return GAME_WIDTH;
    }
    public int getHeight(){
        return GAME_HEIGHT;
    }

    public TankFrame() throws HeadlessException {
        //初始化gameModle
        this.gameModel = new GameModel(this);
        this.setTitle((String) PropertiesMgr.getProperty("gameTitle"));
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
        this.gameModel.paint(g);
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
            Tank myTank = (Tank)(tankFrame.gameModel.getGameObjects().get(0));
            boolean formerIsMoving = myTank.getIsMoving();
            Dir formerDir = myTank.getDir();
            //判断坦克方向
            if (keyCode == KeyEvent.VK_LEFT){
                myTank.setDir(Dir.LEFT);myTank.setIsMoving(true);
                if (formerDir == Dir.RIGHT) myTank.setIsMoving(false);
            }else if (keyCode == KeyEvent.VK_RIGHT){
                myTank.setDir(Dir.RIGHT);myTank.setIsMoving(true);
                if (formerDir == Dir.LEFT) myTank.setIsMoving(false);
            }else if (keyCode == KeyEvent.VK_UP){
                myTank.setDir(Dir.UP);myTank.setIsMoving(true);
                if (formerDir == Dir.DOWN) myTank.setIsMoving(false);
            }else if (keyCode == KeyEvent.VK_DOWN){
                myTank.setDir(Dir.DOWN);myTank.setIsMoving(true);
                if (formerDir == Dir.UP) myTank.setIsMoving(false);
            }
            //按ctrl键开火
            if (keyCode == KeyEvent.VK_CONTROL){
                myTank.getFireStrategy().fire(myTank);
                //TODO 开火
            }
            //坦克启动
            if (formerIsMoving == false && myTank.getIsMoving() == true){
                TankStartMovingMsg tankStartMovingMsg = new TankStartMovingMsg(myTank.getUuid(),myTank.getX(),myTank.getY(),myTank.getDir());
                tankFrame.gameModel.getClient().send(tankStartMovingMsg);new Thread(()->new Audio("audio/tank_move.wav").play()).start();
            }
            //坦克变向
            if (formerDir != myTank.getDir()){
                TankDirChangedMsg tankDirChangedMsg = new TankDirChangedMsg(myTank.getUuid(),myTank.getX(),myTank.getY(),myTank.getDir());
                tankFrame.gameModel.getClient().send(tankDirChangedMsg);new Thread(()->new Audio("audio/tank_move.wav").play()).start();
            }
            //坦克停止
            if (formerIsMoving == true && myTank.getIsMoving() == false){
                TankStopMovingMsg tankStopMovingMsg = new TankStopMovingMsg(myTank.getUuid(),myTank.getX(),myTank.getY(),myTank.getDir());
                tankFrame.gameModel.getClient().send(tankStopMovingMsg);
            }
            //存盘、加载
            if (keyCode == KeyEvent.VK_S) tankFrame.gameModel.save();
            if (keyCode == KeyEvent.VK_L) tankFrame.gameModel.load();
        }

        @Override
        public void keyReleased(KeyEvent e) {
//            ((Tank)(tankFrame.gameModel.getGameObjects().get(0))).setIsMoving(false);
        }

    }

}
