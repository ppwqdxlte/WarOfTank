package com.lgm.model;

import com.lgm.facade.GameModel;
import com.lgm.mgr.ResourceMgr;
import com.lgm.util.Audio;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author:李罡毛
 * @date:2021/2/5 13:18
 */
public class Explode extends GameObject implements Serializable {
    @Serial
    private static final long serialVersionUID = -5927261928218784540L;
    private int x;
    private int y;
    private static int WIDTH,HEIGHT;//帧尺寸
    private int f;//第几帧

    public Explode() {
    }

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodeImages[f++],x,y,null);
        if (f>=ResourceMgr.explodeImages.length){
            GameModel.getInstance().getGameObjects().remove(this);
        }
    }
}
