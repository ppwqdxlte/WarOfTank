package com.lgm;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author:李罡毛
 * @date:2021/2/4 17:17
 */
public class TankFrame extends Frame {
    private int x = 80;
    private int y = 60;
    public TankFrame() throws HeadlessException {
        this.setTitle("坦克大战");
        this.setSize(800,600);
        this.setBackground(Color.pink);
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

    @Override
    public void paint(Graphics g) {
        System.out.println("..............Graphics is painting.............");
        g.fillRect(x,y,50,50);
        x+=40;
        y+=30;
    }
    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
//            repaint();
            System.out.println("Key pressed..........");
        }

        @Override
        public void keyReleased(KeyEvent e) {
//            repaint();
            System.out.println("Key released..........");
        }
    }
}
