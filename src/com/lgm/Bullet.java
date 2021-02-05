package com.lgm;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author:李罡毛
 * @date:2021/2/5 13:18
 */
public class Bullet implements Serializable {
    @Serial
    private static final long serialVersionUID = 8918545667283636286L;
    private int x;
    private int y;
    private Dir dir;
    private final int width = 20;
    private final int SPEED = 20;
    private boolean isLive = true;//子弹撞车或者跃出窗口就移除，等待回收，否则子弹变多后占用内存导致内存溢出
    private TankFrame tankFrame;//获取坦克窗口的私有属性

    public Bullet() {
    }

    public Bullet(int x, int y, Dir dir,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g){
        if (!isLive){
            this.tankFrame.getBullets().remove(this);
            return;
        }
        g.fillOval(x,y,width,width);
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
        if (x<0||y<0||x>tankFrame.getWidth()||y> tankFrame.getHeight()){
            this.isLive = false;
        }
    }
}
