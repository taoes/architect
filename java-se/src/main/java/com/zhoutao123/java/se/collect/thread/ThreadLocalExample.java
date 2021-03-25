package com.zhoutao123.java.se.collect.thread;

import java.util.concurrent.Executors;

public class ThreadLocalExample {
    private static ThreadLocal<String> local = new ThreadLocal<>();

    public static void main(String[] args) {
        local.set("VALUE");
        String s = local.get();
        System.out.println(s);

        // 固定线程数的线程池，无界队列
        Executors.newFixedThreadPool(4);

        // 无上限的线程池，60S回收线程
        Executors.newCachedThreadPool();

        // 单线程池，无上限队列
        Executors.newSingleThreadExecutor();

        // 延迟队列,无上限的延时队列
        Executors.newScheduledThreadPool(4);


    }
}
