package com.lgm;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.lgm.Dir.*;

/**
 * @author:李罡毛
 * @date:2021/2/4 17:17
 */
public class TankFrame extends Frame {
    private int x = 80;
    private int y = 60;
    private Dir dir = Dir.IMMOBILE;//初始化静止
    private static final int SPEED = 10;
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
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default :
                break;
        }
//        x+=SPEED;
//        y+=SPEED;
    }

    class MyKeyAdapter extends KeyAdapter {
//        private boolean bL = false;
//        private boolean bR = false;
//        private boolean bU = false;
//        private boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    dir = LEFT;
                    break;
                case KeyEvent.VK_RIGHT:
                    dir = RIGHT;
                    break;
                case KeyEvent.VK_UP:
                    dir = UP;
                    break;
                case KeyEvent.VK_DOWN:
                    dir = DOWN;
                    break;
                default:
                    dir = IMMOBILE;
                    break;
            }
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
//            int keyCode = e.getKeyCode();
//            switch (keyCode){
//                case KeyEvent.VK_LEFT:
//                    bL = false;
//                    break;
//                case KeyEvent.VK_RIGHT:
//                    bR = false;
//                    break;
//                case KeyEvent.VK_UP:
//                    bU = false;
//                    break;
//                case KeyEvent.VK_DOWN:
//                    bD = false;
//                    break;
//                default:
//                    break;
//            }
            dir = Dir.IMMOBILE;
        }
    }
}
