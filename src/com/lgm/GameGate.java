package com.lgm;

import com.lgm.net.Client;
import com.lgm.util.Audio;
import com.lgm.view.TankFrame;

/**
 * @author:李罡毛
 * @date:2021/2/4 13:13
 * new 一个Frame窗口
 */
public class GameGate {
    public static void main(String[] args) throws InterruptedException {

        TankFrame tankFrame = TankFrame.getInstance();

        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tankFrame.repaint();
            }
        }).start();
        //connect to server,or you can new a thread to run this
        Client client = new Client();
        client.connect();
    }
}
