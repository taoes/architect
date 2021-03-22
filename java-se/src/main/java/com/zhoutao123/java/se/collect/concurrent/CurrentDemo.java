package com.zhoutao123.java.se.collect.concurrent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CurrentDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        int i = atomicInteger.incrementAndGet();

        new AtomicBoolean().compareAndSet(true,false);
    }
}
