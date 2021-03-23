package com.zhoutao123.struct;

import java.util.*;

/**
 * 队列相关问题
 */
public class QueueQuestion {
    public static void main(String[] args) {
        Queue<String> queue = new PriorityQueue<>();

        queue.offer("1");
        boolean offer = queue.offer("2");
        queue.offer("3");



        // 弹出第一个元素
        System.out.println(queue.poll());

        // 返回第一个元素
        System.out.println(queue.element());

        // 返回第一个元素
        System.out.println(queue.peek());


        Collection<Object> objects = Collections.synchronizedCollection(new ArrayList<>());
        System.out.println(objects);
    }
}
