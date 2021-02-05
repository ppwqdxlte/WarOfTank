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

    private final Tank tank ;

    public TankFrame() throws HeadlessException {
        this.tank = new Tank(40,30, IMMOBILE);
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
        this.tank.paint(g);
    }

    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    tank.setDir(LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    tank.setDir(RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    tank.setDir(UP);
                    break;
                case KeyEvent.VK_DOWN:
                    tank.setDir(DOWN);
                    break;
                default:
                    tank.setDir(IMMOBILE);
                    break;
            }
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            tank.setDir(IMMOBILE);
        }
    }
}
