package proxy.standing;

import java.util.Random;

/**
 * @author:李罡毛
 * @date:2021/2/22 10:58
 * 问题：我想记录坦克的移动时间
 * 最简单的办法：修改代码，记录时间
 * 问题2：如果无法改变方法源码呢？
 * 用继承？Tank的子类重写move?
 * v1:使用代理
 */
public class TestV1 {
    public static void main(String[] args) {
        new Proxy(new Tank()).move();
    }
}
interface Movable1{
    void move();
}
class Tank implements Movable1{

    @Override
    public void move() {
        System.out.println("Tank is moving,claclaclacla........");
        try {
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Proxy implements Movable1{
    Tank tank;
    public Proxy(Tank tank){
        this.tank = tank;
    }
    @Override
    public void move(){
        long begin = System.currentTimeMillis();
        tank.move();
        long end = System.currentTimeMillis();
        System.out.println(end-begin);
    }
}
