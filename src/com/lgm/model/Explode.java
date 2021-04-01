package com.lgm.model;

import com.lgm.facade.GameModel;
import com.lgm.mgr.ResourceMgr;
import com.lgm.util.Audio;

import java.awt.*;
import java.io.Serial;

/**
 * @author:李罡毛
 * @date:2021/2/5 13:18
 */
public class Explode extends GameObject {
    @Serial
    private static final long serialVersionUID = -5927261928218784540L;
    private int x;
    private int y;
    private int f;//第几帧
    private GameModel gameModel;

    public Explode() {
    }

    public Explode(int x, int y,GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.gameModel = gameModel;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodeImages[f++],x,y,null);
        if (f>= ResourceMgr.explodeImages.length){
            this.gameModel.getGameObjects().remove(this);
        }
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
