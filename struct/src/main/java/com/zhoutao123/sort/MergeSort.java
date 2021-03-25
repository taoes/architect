package com.zhoutao123.sort;

/**
 * 归并排序算法实现
 *
 * @apiNote
 */
public class MergeSort<T extends Comparable<T>> implements Sort<T> {

  private T[] list;

  @Override
  public T[] sort(T[] list) {
    if (list == null || list.length < 2) {
      return list;
    }
    this.list = list;
    System.out.println("暂无实现");

    return list;
  }

  @Override
  public T[] list() {
    return this.list;
  }
}
