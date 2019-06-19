package com.wzd.reportSystem.demo;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DifferentClassLoaderTest {

    public static void main(String[] args) throws Exception {
        Student student = new Student();
        Class<?> clazz =  Class.forName("com.wzd.reportSystem.demo.Person");
        Object object = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field: fields){
            if(field.getType().getName().equals(Student.class.getName())){
                //关键点！设置私有成员属性为可访问！
                field.setAccessible(true);
                //将已创建的对象赋值
                field.set(object,student);
                //field.set(teacher, student);
            }
        }
        System.out.println("123:"+object);


        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream stream = getClass().getResourceAsStream(fileName);
                if (stream == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[stream.available()];
                    // 将流写入字节数组b中
                    stream.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return super.loadClass(name);
            }
        };
        Object obj = classLoader.loadClass("com.wzd.reportSystem.demo.DifferentClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof DifferentClassLoaderTest);

    }

}
