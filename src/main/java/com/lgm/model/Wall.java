package com.lgm.model;

import com.lgm.facade.GameModel;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author:李罡毛
 * @date:2021/2/19 19:12
 */
public class Wall extends GameObject implements Serializable {
    @Serial
    private static final long serialVersionUID = -7402335168777034330L;
    private int x,y;
    private int width,height;
    private GameModel gameModel;
    private Rectangle rectangle;

    public Wall(int x,int y,int width,int height,GameModel gameModel){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gameModel = gameModel;
        this.rectangle = new Rectangle(x,y,width,height);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(x,y,width,height);
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
    public GameModel getGameModel() {
        return gameModel;
    }
    public Rectangle getRectangle() {
        return rectangle;
    }
}
