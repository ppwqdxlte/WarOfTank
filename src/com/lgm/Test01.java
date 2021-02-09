package com.lgm;

/**
 * @author:李罡毛
 * @date:2021/2/4 13:13
 * new 一个Frame窗口
 */
public class Test01 {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        int initTankCount = Integer.parseInt((String)PropertiesMgr.getProperty("initTankCount"));
        //初始化电脑坦克
        for (int i = 0; i < initTankCount; i++) {
            tankFrame.getTanks().add(new Tank(300+i*50,10,Dir.DOWN,tankFrame,Group.BAD));
            if (i%5 == 0)
            tankFrame.getTanks().add(new Tank(100+i*50,300,Dir.UP,tankFrame,Group.GOOD));
        }
        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
