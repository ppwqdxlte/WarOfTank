package com.lgm;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author:李罡毛
 * @date:2021/2/4 17:17
 */
public class TankFrame extends Frame {
    public TankFrame() throws HeadlessException {
        this.setTitle("坦克大战");
        this.setSize(800,600);
        this.setBackground(Color.pink);
        this.setResizable(false);
        this.setVisible(true);
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
        g.fillRect(200,200,50,50);
    }
}
