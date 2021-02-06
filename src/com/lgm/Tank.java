package com.lgm;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author:李罡毛
 * @date:2021/2/5 10:24
 */
public class Tank implements Serializable {

    @Serial
    private static final long serialVersionUID = 4647626261280633755L;
    private int x;
    private int y;
    private Dir dir;
    private final int SPEED = 10;
    private Dir dirBeforeImmobile;

    public Tank() {
    }

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Dir getDirBeforeImmobile() {
        return dirBeforeImmobile;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Dir getDir() {
        return dir;
    }

    public int getSPEED() {
        return SPEED;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void setDirBeforeImmobile(Dir dirBeforeImmobile) {
        this.dirBeforeImmobile = dirBeforeImmobile;
    }

    public void paint(Graphics g) {
        if (dirBeforeImmobile==null){
            dirBeforeImmobile = Dir.RIGHT;
            g.drawImage(ResourceMgr.tankR,x,y,null);
            this.move();
            return;
        }
        switch (dirBeforeImmobile){
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
        }
        this.move();
    }
    private void move(){
        if (dir == Dir.LEFT){
            x -= SPEED;
        }else if (dir == Dir.RIGHT){
            x += SPEED;
        }else if (dir == Dir.UP){
            y -= SPEED;
        }else if (dir == Dir.DOWN){
            y += SPEED;
        }
    }
}
