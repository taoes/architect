package com.zhoutao123.sort;

/**
 * 希尔排序算法
 *
 * <p>对于希尔排序的算法的时间复杂度比较复杂，因此采用事后分析法估算其时间复杂度</>
 * <li>定义大量的逆序的数据，然后比较两种排序算法，从而预估时间复杂度</>
 *
 * @apiNote 分组优化插入排序,缩小增量排序, 时间复杂度: O(n^(1.3-2))
 */
public class ShellSort<T extends Comparable<T>> implements Sort<T> {

  private T[] list;

  @Override
  public T[] sort(T[] list) {

    if (list == null || list.length < 2) {
      return list;
    }
    this.list = list;
    int length = this.list.length;
    int h = 0;
    // h值的预估算法
    while (h < length) {
      h = 2 * h + 1;
    }

    while (h >= 1) {
      for (int i = h; i < length; i++) {
        for (int j = i; j >= h; j -= h) {
          if (this.list[j].compareTo(this.list[j - h]) < 0) {
            swap(j, j - h);
          } else {
            break;
          }
        }
      }
      h = h >> 1;
    }
    return list;
  }

  @Override
  public T[] list() {
    return this.list;
  }
}
