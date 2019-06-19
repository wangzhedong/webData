package com.wzd.reportSystem.demo.dynamicProxy;

public class Hello implements IHello {
    @Override
    public void sayHello() {
        System.out.println("hello");
    }
}
