package com.zhoutao123.sort;

/**
 * 插入排序算法实现
 *
 * @apiNote 遍历数据，将大数和旁边的交换，每次遍历将大数放在最顶部，完成排序,时间复杂度: O(n^2)
 */
public class InsertSort<T extends Comparable<T>> implements Sort<T> {

  private T[] list;

  @Override
  public T[] sort(T[] list) {
    if (list == null || list.length < 2) {
      return list;
    }
    this.list = list;
    int foreachIndex = list.length;

    for (int i = 1; i < foreachIndex; i++)
      for (int j = i; j > 0; j--) {
        if (this.list[j].compareTo(this.list[j - 1]) < 0) {
          swap(j, j - 1);
          continue;
        }
        break;
      }
    return list;
  }

  @Override
  public T[] list() {
    return this.list;
  }
}
