package com.wzd.reportSystem.demo.dynamicProxy;

import java.lang.reflect.Proxy;

public class TestDynamicProxy {


    public static void  main(String[] args){
        IHello hello = new Hello();
        LoggerHandler handler = new LoggerHandler(hello);
        IHello proxy = (IHello)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),hello.getClass().getInterfaces(),handler);
        proxy.sayHello();
    }
}
