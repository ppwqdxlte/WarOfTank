package com.lgm.net;

/**
 * @author:李罡毛
 * @date:2021/3/29 15:55
 */
public class TankMsg {
    public int x,y;
    public TankMsg(int x,int y){
        this.x=x;
        this.y=y;
    }
    @Override
    public String toString(){
        return "TankMsg:"+x+","+y;
    }
}
