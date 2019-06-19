package com.wzd.reportSystem.demo.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggerHandler implements InvocationHandler {

    private Object target;

    public LoggerHandler(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("start log");
        Object result = method.invoke(target,args);
        System.out.println("en log");
        return result;
    }
}
