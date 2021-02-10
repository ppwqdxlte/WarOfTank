package DesignPatterns.singleton;

/**
 * @author:李罡毛
 * @date:2021/2/10 17:25
 * 饿汉式
 */
public class Mgr1 {
    private Mgr1(){}
    private static final Mgr1 INSTANCE = new Mgr1();
    public Mgr1 getInstance(){
        return Mgr1.INSTANCE;
    }
}
