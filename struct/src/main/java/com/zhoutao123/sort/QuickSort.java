package com.zhoutao123.sort;

/** 快速排序的算法实现 */
public class QuickSort<T extends Comparable<T>> implements Sort<T> {

  private T[] list;

  @Override
  public T[] sort(T[] list) {

    if (list == null || list.length < 2) {
      return list;
    }
    this.list = list;
    sort(0, this.list.length);
    return list;
  }

  private void sort(int begin, int end) {
    if (end - begin < 2) {
      return;
    }
    // 确定轴点位置
    int p = partition(begin, end);

    // 确定轴点位置后对左右分别进行排序
    sort(begin, p);
    sort(p + 1, end);
  }

  /** 计算轴点的最终位置 */
  private int partition(int begin, int end) {
    T p = this.list[begin];
    end--;
    while (begin < end) {
      while (begin < end) {
        // 如果右边的比P大，那么只移动索引,否则和begin复制数据
        if (this.list[end].compareTo(p) > 0) {
          end--;
        } else {
          this.list[begin++] = this.list[end];
          break;
        }
      }

      while (begin < end) {
        // 如果左边的比P小，那么只移动索引，否则和end复制数据
        if (this.list[begin].compareTo(p) <= 0) {
          begin++;
        } else {
          this.list[end--] = this.list[begin];
          break;
        }
      }
    }
    this.list[begin] = p;
    return begin;
  }

  @Override
  public T[] list() {
    return this.list;
  }
}
