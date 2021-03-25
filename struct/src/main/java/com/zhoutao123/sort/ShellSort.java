package com.zhoutao123.sort;

/**
 * 选择排序算法
 *
 * @apiNote 从中选出最小的数据和头部的数据交换，然后进行下次遍历，时间复杂度 O(n^2)
 */
public class SelectionSort<T extends Comparable<T>> implements Sort<T> {

  private T[] list;

  @Override
  public T[] sort(T[] list) {
    if (list == null || list.length < 2) {
      return list;
    }
    this.list = list;

    int foreachIndex = list.length - 1;

    for (int i = 0; i < foreachIndex; i++) {
      int minIndex = findMinIndex(i);
      swap(i, minIndex);
    }
    return list;
  }

  private int findMinIndex(int startIndex) {
    int minIndex = startIndex;
    for (int i = startIndex + 1; i < this.list.length; i++) {
      if (this.list[i].compareTo(this.list[minIndex]) < 0) {
        minIndex = i;
      }
    }
    return minIndex;
  }

  @Override
  public T[] list() {
    return this.list;
  }
}
