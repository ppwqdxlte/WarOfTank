package proxy.dynamic;

import java.util.Random;

/**
 * @author:李罡毛
 * @date:2021/2/22 14:11
 * * 【【【使用jdk的动态代理】】】缺点：被代理者必须要实现接口，如果没有接口就没法用JDK的动态代理
 *  * 问题：如果被代理者没有实现任何接口，那要怎么代理它呢？？测试请见HelloSpring模块的Code-generator lib
 *  *         简称cglib
 */
public class TestV4 {
    {

        /*
        Maven管理依赖的时候：
        pom.xml添加依赖
        <!-- https://mvnrepository.com/artifact/cglib/cglib -->
        <dependency>
            <groupId>cglib</groupId>
            <artifactId>cglib</artifactId>
            <version>3.2.12</version>
        </dependency>

        gradle管理依赖的时候：
        build.gradle添加依赖

        */


    }

    public static void main(String[] args) {
       /*
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Tank.class);
        enhancer.setCallback(new TimeMethodInterceptor());
        Tank tank = (Tank)enhancer.create();
        tank.move();
        */
    }
    /*
    class TimeMethodInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

            System.out.println(o.getClass().getSuperclass().getName());
            System.out.println("before");
            Object result = null;
            result = methodProxy.invokeSuper(o, objects);
            System.out.println("after");
            return result;
        }
    }
    */

    class Tank {
        public void move() {
            System.out.println("Tank moving claclacla...");
            try {
                Thread.sleep(new Random().nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
