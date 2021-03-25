package com.zhoutao123.java.se.collect.thread;

import java.util.concurrent.TimeUnit;

/**
 * 线程的Join操作
 */
public class ThreadJoin {

    // final  adj: 不可变的
    // finally adv: 完成
    // finalize vt: 完成

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1 开始执行...");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ignore) {

            }


        });
        Thread t2 = new Thread(() -> {
            try {
                System.out.println("t2 等待 t1 执行完成");
                t1.join();
                System.out.println("开始执行t2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread t3 = new Thread(() -> {
            try {
                System.out.println("t3 等待 t2 执行完成");
                t2.join();
                System.out.println("开始执行t3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
