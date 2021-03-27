package com.zhoutao123.sort;

/** 堆排序 */
public class HeapSort<T extends Comparable<T>> implements Sort<T> {

  private T[] list;

  /** 原地建堆 */
  private void heapify() {}

  private void heap_build() {}

  private void head_sort() {}

  @Override
  public T[] sort(T[] list) {

    if (list == null || list.length < 2) {
      return list;
    }
    this.list = list;
    heapify();

    return list;
  }

  @Override
  public T[] list() {
    return this.list;
  }
}
