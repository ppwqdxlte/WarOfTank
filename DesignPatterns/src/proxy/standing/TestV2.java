package proxy.standing;

import java.util.Random;

/**
 * @author:李罡毛
 * @date:2021/2/22 11:09
 * 问题：我想记录坦克的移动时间
 * 最简单的办法：修改代码，记录时间
 * 问题2：如果无法改变方法源码呢？
 * 用继承？
 * v1:使用代理
 * v2:代理有各种类型
 * 问题：如何实现代理的各种组合？继承？Decorator?
 * 代理的对象改成Movable类型-越来越像decorator了
 */
public class TestV2 {
    public static void main(String[] args) {
        new TankLogProxy(new TankTimeProxy(new Tank2())).move();
    }
}
interface Movable2{
    void move();
}
class Tank2 implements Movable2{

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
class TankTimeProxy implements Movable2{
    Movable2 movable2;

    public TankTimeProxy(Movable2 movable2) {
        this.movable2 = movable2;
    }

    @Override
    public void move() {
        long begin = System.currentTimeMillis();
        movable2.move();
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}
class TankLogProxy implements Movable2{
    Movable2 movable2;

    public TankLogProxy(Movable2 movable2) {
        this.movable2 = movable2;
    }

    @Override
    public void move() {
        System.out.println("start...");
        movable2.move();
        System.out.println("...stop");
    }
}
