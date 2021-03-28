package com.zhoutao123.recall;

import java.util.*;

/**
 * 全排列问题
 *
 * @apiNote 使用回溯算法实现
 */
public class FullPermutation {

  private List<List<Integer>> res = new LinkedList<>();

  public static void main(String[] args) {
    int[] numbers = {1, 2, 4};
    FullPermutation fullPermutation = new FullPermutation();
    fullPermutation.permutation(numbers);

    // 输出结果
    for (List<Integer> integers : fullPermutation.res) {
      System.out.println(Arrays.toString(integers.toArray()));
    }
  }

  public void permutation(int[] num) {
    LinkedList<Integer> track = new LinkedList<>();
    track(num, track);
  }

  public void track(int[] num, LinkedList<Integer> track) {
    if (track.size() == num.length) {
      this.res.add(new LinkedList<>(track));
      return;
    }

    for (int i = 0; i < num.length; i++) {
      if (track.contains(num[i])) {
        continue;
      }
      track.add(num[i]);

      // 进入下一层
      track(num, track);

      // 回溯过程
      track.removeLast();
    }
  }
}
