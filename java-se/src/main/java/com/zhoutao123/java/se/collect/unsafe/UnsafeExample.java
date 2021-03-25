package com.zhoutao123.java.se.collect.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe 相关操作类
 */
public class UnsafeExample {

    public static void main(String[] args) throws Exception {
        Unsafe unsafe = getUnsafeInstance();
        long offset = unsafe.objectFieldOffset(ExampleClass.class.getDeclaredField("name"));
        System.out.println(offset);

    }

    private static Unsafe getUnsafeInstance() throws Exception {
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        return (Unsafe) field.get(null);
    }

    static class ExampleClass {
        private String name;
        private int age;
    }

}
