package com.lgm;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author:李罡毛
 * @date:2021/2/4 13:13
 */
public class Test {
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setBackground(Color.lightGray);
        frame.setResizable(false);
        frame.setTitle("坦克大战");
        frame.setSize(800,600);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
