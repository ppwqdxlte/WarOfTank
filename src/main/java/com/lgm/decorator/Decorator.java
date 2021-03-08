package com.lgm.decorator;

import com.lgm.model.GameObject;

import java.awt.*;
import java.io.Serializable;

/**
 * @author:李罡毛
 * @date:2021/2/20 15:51
 */
public abstract class Decorator extends GameObject implements Serializable {
    protected GameObject gameObject;
    public Decorator(GameObject gameObject){
        this.gameObject = gameObject;
    }
    @Override
    public abstract void paint(Graphics g);
}
