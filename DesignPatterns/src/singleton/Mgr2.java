package singleton;

/**
 * @author:李罡毛
 * @date:2021/2/10 17:27
 * 懒汉式  1.同步方法
 */
public class Mgr2 {
    private Mgr2(){}
    private static volatile/*防止指令重排没初始化就返回mgr2*/ Mgr2 mgr2;
    public synchronized static Mgr2 getInstance(){
        if (mgr2 == null){
            mgr2 = new Mgr2();
        }
        return mgr2;
    }
}
