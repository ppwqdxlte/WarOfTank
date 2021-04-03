package com.lgm.decorator;

import com.lgm.model.GameObject;

import java.awt.*;
import java.util.UUID;

/**
 * @author:李罡毛
 * @date:2021/2/20 17:04
 */
public class TailDecorator extends Decorator {
    private UUID uuid = UUID.randomUUID();

    public TailDecorator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paint(Graphics g) {

        gameObject.paint(g);

        this.x = gameObject.x;
        this.y = gameObject.y;

        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawLine(this.x,this.y,x+this.getWidth(),y+this.getHeight());
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

    @Override
    public UUID getUuid() {
        return uuid;
    }
}
