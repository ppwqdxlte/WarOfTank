package com.lgm.model;

import java.awt.*;
import java.io.Serial;

/**
 * @author:李罡毛
 * @date:2021/2/19 19:12
 */
public class Wall extends GameObject {
    @Serial
    private static final long serialVersionUID = -7402335168777034330L;
//    private int x,y;
    private int width,height;
    private Rectangle rectangle;

    public Wall(int x,int y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rectangle = new Rectangle(x,y,width,height);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.PINK);
        g.fillRect(x,y,width,height);
        g.setColor(color);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Rectangle getRectangle() {
        return rectangle;
    }
}
