package com.lgm.model;
import java.awt.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author:李罡毛
 * @date:2021/2/18 15:42
 */
public abstract class GameObject implements Serializable {
    public int x;
    public int y;
    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();

    public abstract UUID getUuid();
}
