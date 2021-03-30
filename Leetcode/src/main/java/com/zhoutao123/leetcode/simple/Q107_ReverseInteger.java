package com.zhoutao123.leetcode.simple;

/**
 * Q007: 整数反转
 *
 * <p>执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
 *
 * <p>内存消耗： 35.8 MB , 在所有 Java 提交中击败了 23.49% 的用户
 *
 * @apiNote https://leetcode-cn.com/problems/reverse-integer
 */
public class Q107_ReverseInteger {

  public int reverse(int x) {
    long rs = 0;
    while (x != 0) {
      rs = rs * 10 + x % 10;
      x = x / 10;
    }
    if (rs > Integer.MAX_VALUE || rs < Integer.MIN_VALUE) {
      return 0;
    }
    return (int) rs;
  }
}
