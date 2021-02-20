package com.lgm.decorator;

import com.lgm.model.GameObject;

import java.awt.*;

/**
 * @author:李罡毛
 * @date:2021/2/20 15:59
 */
public class RectDecorator extends Decorator{

    public RectDecorator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paint(Graphics g) {

        gameObject.paint(g);

        this.x = gameObject.x;
        this.y = gameObject.y;

        Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawRect(this.x,this.y,this.getWidth(),this.getHeight());
        g.setColor(color);
    }

    @Override
    public int getWidth() {
        return gameObject.getWidth();
    }

    @Override
    public int getHeight() {
        return gameObject.getHeight();
    }
}
