package com.zhoutao123.java.se.collect.code;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Code {

    public static void main(String[] args) {
        Student student = () -> System.out.println("正在说活");
        Student o = (Student) Proxy.newProxyInstance(Code.class.getClassLoader(), new Class[]{Student.class}, new StudentProxy(student));
        o.speak();

    }


    static class StudentProxy implements InvocationHandler {

        Student student;

        public StudentProxy(Student student) {
            this.student = student;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Before");
            Object invoke = method.invoke(student, args);
            System.out.println("After");
            return invoke;
        }
    }


    @FunctionalInterface
    static interface Student {

        void speak();

    }


}
