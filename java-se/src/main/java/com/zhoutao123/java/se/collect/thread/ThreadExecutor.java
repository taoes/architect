package com.zhoutao123.java.se.collect.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池相关
 */
public class ThreadExecutor {

    public static void main(String[] args) {
        // 线程池的饱和策略
        // 拒绝策略: ThreadPoolExecutor.AbortPolicy;
        // 调用者运行策略: ThreadPoolExecutor.CallerRunsPolicy
        // 丢弃策略: ThreadPoolExecutor.DiscardPolicy
        // 丢弃最早未执行策略 ThreadPoolExecutor.DiscardOldestPolicy

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println("运行线程池:" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ignore) {

                }
            });
        }


    }
}
