package com.lgm;

import java.io.IOException;
import java.util.Properties;

/**
 * @author:李罡毛
 * @date:2021/2/9 11:44
 */
public class PropertiesMgr {
    /**
     * 私有化无参构造方法，单粒模式
     */
    private PropertiesMgr(){}
    
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(PropertiesMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getProperty(String key) {
        if (properties == null) return null;
        return properties.get(key);
    }
}
