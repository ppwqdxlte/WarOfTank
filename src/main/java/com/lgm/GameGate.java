package com.lgm;

import com.lgm.util.Audio;
import com.lgm.view.TankFrame;

/**
 * @author:李罡毛
 * @date:2021/2/4 13:13
 * new 一个Frame窗口
 */
public class GameGate {
    public static void main(String[] args) throws InterruptedException {

        TankFrame tankFrame = new TankFrame();

        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
