package com.lgm.ASM;

/**
 * @author:李罡毛
 * @date:2021/2/25 17:25
 */
public class MyClassLoader extends ClassLoader{
    public Class defineClass(String name,byte[] b){
        return defineClass(name,b,0,b.length);
    }
}
