package com.zhoutao123.leetcode.simple;

/**
 * Q009: 回文数
 *
 * <p>解法1： 执行用时： 18 ms , 在所有 Java 提交中击败了 10.41% 的用户 内存消耗： 38 MB , 在所有 Java 提交中击败了 37.14% 的用户
 *
 * <p>解法2： 执行用时： 11 ms , 在所有 Java 提交中击败了 47.21% 的用户 内存消耗： 37.9 MB , 在所有 Java 提交中击败了 53.71% 的用户
 *
 * @apiNote https://leetcode-cn.com/problems/palindrome-number
 */
public class Q009_PalindromeNumber {

  public static void main(String[] args) {
    boolean result = new Q009_PalindromeNumber().is(123454321);
    System.out.println(result);

    result = new Q009_PalindromeNumber().is2(123454321);
    System.out.println(result);
  }

  public boolean is(int x) {
    long y = x;
    if (y < 0) {
      return false;
    }
    int i = 0;
    while (y / power(i++) != 0) {}

    int j = --i;
    while (j > 1) {
      j--;
      long power = power(j);
      long p = y / power;
      long l = y % 10;
      if (p != l) {
        return false;
      }
      y = y % power / 10;
      j--;
    }
    return true;
  }

  private long power(int i) {
    return (long) Math.pow(10, i);
  }

  /** 转换为字符串，通过字符串的实现双指针计算 */
  public boolean is2(int x) {
    if (x < 0) {
      return false;
    }
    if (x < 10) {
      return true;
    }

    String data = String.valueOf(x);
    int p1 = 0;
    int p2 = data.length() - 1;
    while (p1 < p2) {
      if (data.charAt(p1++) != data.charAt(p2--)) {
        return false;
      }
    }
    return true;
  }
}
