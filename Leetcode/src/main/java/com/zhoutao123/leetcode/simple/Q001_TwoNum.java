package com.zhoutao123.leetcode.simple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Q001: 两数之和
 *
 * <p>执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
 *
 * <p>内存消耗： 38.9 MB , 在所有 Java 提交中击败了 8.33% 的用户
 *
 * @apiNote https://leetcode-cn.com/problems/two-sum/
 */
public class Q001_TwoNum {

  public static void main(String[] args) {
    int[] result = new Q001_TwoNum().twoSum(new int[] {3, 3}, 6);
    System.out.println(Arrays.toString(result));
  }

  public int[] twoSum(int[] nums, int target) {
    if (nums == null || nums.length < 2) {
      return new int[] {};
    }
    Map<Integer, Integer> valueMap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (valueMap.containsKey(nums[i])) {
        return new int[] {valueMap.get(nums[i]), i};
      } else {
        valueMap.put(target - nums[i], i);
      }
    }
    return new int[] {};
  }
}
