package com.zhoutao123.java.se.collect.thread;

import java.util.concurrent.TimeUnit;

/**
 * 线程死锁
 */
public class ThreadDeadLock {

    public static void main(String[] args) {
        String a = "DEMO";
        String b = "DEMO";
        deadLock2(a, b);
    }



    public static void deadLock2(String a, String b) {
        new Thread(() -> {
            synchronized (a) {
                try {
                    System.out.println(Thread.currentThread().getName() + "获取到A锁");
                    TimeUnit.SECONDS.sleep(1);
                    synchronized (b) {
                        System.out.println(Thread.currentThread().getName() + "获取到B锁");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程A").start();

        new Thread(() -> {
            synchronized (b) {
                try {
                    System.out.println(Thread.currentThread().getName() + "获取到B锁");
                    TimeUnit.SECONDS.sleep(2);
                    synchronized (a) {
                        System.out.println(Thread.currentThread().getName() + "获取到A锁");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程B").start();
    }
}
