package com.zhoutao123.java.se.collect.thread;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * 线程等待相关实现
 */
public class ThreadWait {


    public static void main(String[] args) throws Exception {
        // 参与的线程不一致，有的是等待倒计时，有的是执行任务
        //countDownLatch();

        //参与的线程职责一致，都是在等待其他线程达到
        //cyclicBarrier();

        // JDK8 提供的CompletableFuture
        completableFuture();
    }


    /**
     * JDK 8 提供的 CompletableFuture
     */
    public static void completableFuture() {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("ThreadWait.completableFuture.............1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "OK1";
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("ThreadWait.completableFuture.............2");
            return "OK2";
        });
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("ThreadWait.completableFuture.............3");
            return "OK2";
        });


        CompletableFuture<Void> future = CompletableFuture.allOf(f1, f2, f3);
        try {
            System.out.println("COMPLETABLE START");
            future.join();
            assert Objects.equals(f1.get(), "OK1");
            assert Objects.equals(f2.get(), "OK2");
            assert Objects.equals(f3.get(), "OK3");
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("COMPLETABLE DONE");
    }


    /**
     * 循环屏障
     */
    public static void cyclicBarrier() {
        CyclicBarrier barrier = new CyclicBarrier(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {

                try {
                    System.out.println(Thread.currentThread().getName() + "----> Start");
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + "----> OK");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }, "线程:" + i).start();
        }

    }

    /**
     * 门栓计数
     */
    public static void countDownLatch() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int delay = i;
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "----> Start！");
                    TimeUnit.SECONDS.sleep(delay + 2);
                    System.out.println(Thread.currentThread().getName() + "----> OK");
                } catch (InterruptedException ignore) {

                } finally {
                    latch.countDown();
                }
            }, "线程:" + i).start();
        }
        latch.await();
        System.out.println("程序全部执行完成");
    }
}
