package DesignPatterns.singleton;

/**
 * @author:李罡毛
 * @date:2021/2/10 17:29
 * 懒汉式  2.同步代码块
 */
public class Mgr3 {
    private Mgr3(){}
    private static Mgr3 mgr3;
    public static Mgr3 getInstance(){
        if (mgr3 == null){
            synchronized (Mgr3.class){
                if (mgr3 == null){
                    mgr3 = new Mgr3();
                }
            }
        }
        return mgr3;
    }
}
