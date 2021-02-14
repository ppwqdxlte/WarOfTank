package singleton;

/**
 * @author:李罡毛
 * @date:2021/2/10 17:31
 * 枚举唯一值，线程安全，防止反序列化出来的对象不是原来的对象，就是说反序列化之后依然单粒！！！
 */
public enum Mgr5 {
    INSTANCE
}
