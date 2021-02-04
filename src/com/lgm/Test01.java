package com.lgm;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author:李罡毛
 * @date:2021/2/4 13:13
 * new 一个Frame窗口
 */
public class Test01 {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        /*//不需要自动移动，接下来尝试方向键控制方块的运动
        while (true){
            Thread.sleep(300);
            tankFrame.repaint();
        }*/
    }
}
