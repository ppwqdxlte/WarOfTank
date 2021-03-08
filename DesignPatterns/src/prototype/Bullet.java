package prototype;

import java.io.Serializable;

/**
 * @author:李罡毛
 * @date:2021/3/8 21:32
 */
public class Bullet implements Cloneable {
    int weidth,height;
    Location location;
    private Bullet(){}
    public Bullet(int weidth,int height,Location location){
        this.weidth = weidth;
        this.height = height;
        this.location = location;
    }
    @Override
    public Bullet clone() throws CloneNotSupportedException {
        /*浅克隆*/
//        Bullet newBullet = (Bullet) super.clone();
        /*深克隆*/
        Bullet newBullet =(Bullet) super.clone();
        newBullet.location = (Location) location.clone();
        return newBullet;
    }
}
class Location implements Cloneable{
    int x,y;
    private Location(){}
    public Location(int x,int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public Location clone() throws CloneNotSupportedException {
        return (Location) super.clone();
    }
}
