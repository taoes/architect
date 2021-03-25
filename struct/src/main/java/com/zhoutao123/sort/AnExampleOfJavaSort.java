package com.zhoutao123.sort;

import java.util.Arrays;
import java.util.Random;

/** Java 实现排序的相关接口 */
public class JavaSort {

  public static void main(String[] args) {
    Integer[] waitSortArray = new Integer[20];
    Random random = new Random();
    for (int i = 0; i < waitSortArray.length; i++) {
      int randomNumber = random.nextInt(100);
      waitSortArray[i] = randomNumber;
    }

    System.out.print("未排序数组:");
    print(waitSortArray);
    System.out.println();

    long start = System.nanoTime();
    System.out.print("冒泡排序法:");
    print(new BubbleSort<Integer>().sort(waitSortArray.clone()));
    System.out.println("耗时:" + (System.nanoTime() - start) /100000.0 + "毫秒");

    System.out.print("选择排序法:");
    start = System.nanoTime();
    print(new SelectionSort<Integer>().sort(waitSortArray.clone()));
    System.out.println("耗时:" + (System.nanoTime() - start) /100000.0 + "毫秒");

    System.out.print("插入排序法:");
    start = System.nanoTime();
    print(new InsertionSort<Integer>().sort(waitSortArray.clone()));
    System.out.println("耗时:" + (System.nanoTime() - start) /100000.0 + "毫秒");

    System.out.print("希尔排序法:");
    start = System.nanoTime();
    print(new ShellSort<Integer>().sort(waitSortArray.clone()));
    System.out.println("耗时:" + (System.nanoTime() - start) /100000.0 + "毫秒");
  }

  public static <T> void print(T[] data) {
    if (data == null || data.length == 0) {
      return;
    }
    System.out.println(Arrays.toString(data));
  }
}
