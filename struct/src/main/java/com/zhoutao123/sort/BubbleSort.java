package com.zhoutao123.sort;

/**
 * 冒泡排序算法实现
 *
 * @apiNote 遍历数据，将大数和旁边的交换，每次遍历将大数放在最顶部，完成排序,时间复杂度: O(n^2)
 */
public class BubbleSort<T extends Comparable<T>> implements Sort<T> {

  private T[] list;

  @Override
  public T[] sort(T[] list) {
    if (list == null || list.length < 2) {
      return list;
    }
    this.list = list;

    int foreachIndex = list.length - 1;

    // 每一次遍历的时候，将大数字和前一个数字交换
    for (int j = 0; j < foreachIndex; j++) {
      for (int i = 0; i < foreachIndex - j; i++) {
        if (list[i].compareTo(list[i + 1]) > 0) {
          swap(i, i + 1);
        }
      }
    }
    return list;
  }

  @Override
  public T[] list() {
    return this.list;
  }
}
