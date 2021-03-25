package com.zhoutao123.java.se.collect.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 原子操作系列
 */
public class AtomicIntegerExample {

    // 基于CAS实现
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(1);
        integer.getAndIncrement();

        // 解决ABA问题，通常 AtomicMarkableReference 使用布尔类型标记，通常只能降低ABA问题的概率
        // AtomicMarkableReference<String> reference;
        AtomicStampedReference<String> reference = new AtomicStampedReference<>("s", 0);
        reference.compareAndSet("s", "ss", 0, 1);
        int stamp = reference.getStamp();
        System.out.println(stamp);
    }
}
