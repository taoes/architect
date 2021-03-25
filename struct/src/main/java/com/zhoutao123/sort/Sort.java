package com.zhoutao123.sort;

public interface Sort<T> {

  /** 排序实现方法 */
  T[] sort(T[] sets);

  T[] list();

  default void swap(int i, int j) {
    T[] data = list();
    T t = data[i];
    data[i] = data[j];
    data[j] = t;
  }
}
