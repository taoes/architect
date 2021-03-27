package com.zhoutao123.sort;

/**
 * 归并排序算法实现
 *
 * @apiNote
 */
@SuppressWarnings("unchecked")
public class MergeSort<T extends Comparable<T>> implements Sort<T> {

  private T[] list;

  // 辅助数组
  private T[] assistant;

  @Override
  public T[] sort(T[] list) {
    if (list == null || list.length < 2) {
      return list;
    }
    this.list = list;
    this.assistant = (T[]) new Comparable[this.list.length >> 1];
    sort(0, this.list.length);
    return list;
  }

  private void sort(int begin, int end) {
    if (end - begin <= 1) {
      return;
    }
    int mid = (begin + end) >> 1;
    sort(begin, mid);
    sort(mid, end);
    merge(begin, mid, end);
  }

  /**
   * 将 [begin,mid) 和 [mid,end) 合并
   *
   * @apiNote 双指针的方式合并
   */
  private void merge(int begin, int mid, int end) {
    // 备份左数组
    if (mid - begin >= 0) {
      System.arraycopy(this.list, begin, assistant, 0, mid - begin);
    }

    // 定义三指针
    int p1 = begin;
    int p2 = mid;
    int sp = begin;

    // 如果两者都没有结束，则比较并替换
    while (p1 < mid && p2 < end) {
      if (this.assistant[p1 - begin].compareTo(this.list[p2]) <= 0) {
        this.list[sp++] = this.assistant[p1++ - begin];
      } else {
        this.list[sp++] = this.list[p2++];
      }
    }

    // 如果左边结束，则不作处理，右边结束，则将左边的继续拷贝
    if (p2 >= end) {
      while (p1 < mid) {
        if (sp == 3 && p1 == 2) {
          System.out.println();
        }
        this.list[sp++] = this.assistant[p1++ - begin];
      }
    }
  }

  @Override
  public T[] list() {
    return this.list;
  }
}
