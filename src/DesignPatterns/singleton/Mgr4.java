package DesignPatterns.singleton;

/**
 * @author:李罡毛
 * @date:2021/2/10 17:31
 * 静态内部类
 */
public class Mgr4 {
    private Mgr4(){}

    public static Mgr4 getInstance(){
        return Mgr4Holder.INSTANCE;
    }

    private static class Mgr4Holder{
        private static final Mgr4 INSTANCE = new Mgr4();
    }
}
