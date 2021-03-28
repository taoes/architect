package com.zhoutao123.sort;

/** 堆排序 */
public class HeapSort<T extends Comparable<T>> implements Sort<T> {

  private T[] list;

  /** 原地建堆 */
  private void heapify(int i, int n) {
    if (i > n) {
      return;
    }
    int c1 = 2 * i + 1;
    int c2 = 2 * i + 2;
    int max = i;
    if (c2 < n && list[c1].compareTo(list[max]) > 0) {
      max = c1;
    }

    if (c2 < n && list[c2].compareTo(list[max]) > 0) {
      max = c2;
    }

    if (max != i) {
      swap(i, max);
      heapify(max, n);
    }
  }

  private void head_sort() {
    for (int i = (this.list.length - 2) >> 1; i >= 0; i--) {
      heapify(i, this.list.length - 1);
    }

    int n = this.list.length;

    for (int i = 0; i < n; i++) {
      swap(0, this.list.length - i - 1);
      heapify(0, this.list.length - i - 1);
    }
  }

  @Override
  public T[] sort(T[] list) {
    if (list == null || list.length < 2) {
      return list;
    }
    this.list = list;
    head_sort();

    return list;
  }

  @Override
  public T[] list() {
    return this.list;
  }
}
