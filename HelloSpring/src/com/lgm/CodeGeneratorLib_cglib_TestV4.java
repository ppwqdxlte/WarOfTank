package com.lgm;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @author:李罡毛
 * @date:2021/2/22 14:25
 * cglib实现的动态代理，比JDK自带的动态代理，优化于不需要被代理者实现什么接口
 * 问题：https://segmentfault.com/a/1190000037728842
 *              JDK都可以实现多重代理，那么Cglib到底可不可以？
 * 答案：单纯靠Cglib好像不行，借助SpringAOP可以实现出来
 */
public class CodeGeneratorLib_cglib_TestV4 {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SpringTank.class);
        enhancer.setCallback(new LogInterceptor());
        SpringTank springTank = (SpringTank) enhancer.create();
        springTank.move();
        springTank.fire();

        enhancer.setCallback(new TimeInterceptor());
        SpringTank springTank1 = (SpringTank) enhancer.create();
        springTank1.fire();
    }
}
class TimeInterceptor implements MethodInterceptor{

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before");
        System.out.println(obj.getClass().getSuperclass().getName());
        Object o = proxy.invokeSuper(obj, args);
        System.out.println("after");
        return o;
    }
}
class LogInterceptor implements MethodInterceptor{

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("开始时间："+System.currentTimeMillis());
        Object o = proxy.invokeSuper(obj, args);
        System.out.println("结束时间："+System.currentTimeMillis());
        System.out.println("--------------------------------");
        return o;
    }
}
