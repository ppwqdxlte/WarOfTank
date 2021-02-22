package proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

/**
 * @author:李罡毛
 * @date:2021/2/22 11:23
 * 问题：我想记录坦克的移动时间
 * 最简单的办法：修改代码，记录时间
 * 问题2：如果无法改变方法源码呢？
 * 用继承？
 * v01:使用代理
 * v02:代理有各种类型
 * 问题：如何实现代理的各种组合？继承？Decorator?
 * v02:代理的对象改成Movable类型-越来越像decorator了
 * v03:如果有stop方法需要代理...
 * 如果想让LogProxy可以重用，不仅可以代理Tank，还可以代理任何其他可以代理的类型 Object
 * （毕竟日志记录，时间计算是很多方法都需要的东西），这时该怎么做呢？
 * 分离代理行为与被代理对象
 * 【使用jdk的动态代理】
 */
public class TestV3 {
    public static void main(String[] args) {

        //reflection 通过二进制字节码分析类的属性和方法
        //log代理坦克move
        Movable3 movable3 = (Movable3) Proxy.newProxyInstance(Tank3.class.getClassLoader(),
                new Class[]{Movable3.class},
                new LogHandler(new Tank3()));

        movable3.move();
        //log代理子弹move，实现了logHandler的重用
        Movable3 movable31 = (Movable3) Proxy.newProxyInstance(Bullet3.class.getClassLoader(),
                new Class[]{Movable3.class},
                new LogHandler(new Bullet3()));
        movable31.move();
        //log,time共同代理tanke move
        Movable3 movable32 = (Movable3) Proxy.newProxyInstance(Tank3.class.getClassLoader(),
                new Class[]{Movable3.class},
                new TimeHandler(new Tank3()));
        Movable3 movable33 = (Movable3) Proxy.newProxyInstance(Tank3.class.getClassLoader(),
                new Class[]{Movable3.class},
                new LogHandler(movable32));
        movable33.move();
    }
}
interface Movable3{
    void move();
}
class Tank3 implements Movable3{

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
class Bullet3 implements Movable3{

    @Override
    public void move() {
        System.out.println("Let the bullet fly for a while...");
        try {
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class LogHandler implements InvocationHandler{

    Object proxied;//被代理对象

    public LogHandler(Object proxied) {

        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName()+"\tis running....");
        Object invoke = method.invoke(proxied, args);
        System.out.println(method.getName()+"\tis stopped.");
        System.out.println("______________________________________________");
        return invoke;
    }
}
class TimeHandler implements InvocationHandler{

    Object proxied;

    public TimeHandler(Object proxied) {
        this.proxied = proxied;
    }

    private long begin(){
        return System.currentTimeMillis();
    }
    private long end(){
        return System.currentTimeMillis();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long begin = begin();
        Object invoke = method.invoke(proxied, args);
        long end = end();
        System.out.println("用时"+(end-begin)+"毫秒.");
        return invoke;
    }
}
