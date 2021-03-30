package com.zhoutao123.leetcode.simple;

/**
 * S125: 验证回文串，只考虑数字和字母，忽略其他字符
 *
 * <p>执行用时： 2 ms , 在所有 Java 提交中击败了 99.86% 的用户
 *
 * <p>内存消耗： 38.3 MB , 在所有 Java 提交中击败了 91.59% 的用户
 *
 * @apiNote https://leetcode-cn.com/problems/valid-palindrome/
 */
public class Q125_ValidatePalindromeString {

  public static void main(String[] args) {
    Q125_ValidatePalindromeString instance = new Q125_ValidatePalindromeString();
    boolean result = instance.isPalindrome("0P");
    System.out.println(result);
    result = instance.isPalindrome("race a car");
    System.out.println(result);
  }

  /** 双指针实现回文串验证 */
  public boolean isPalindrome(String s) {
    if (s == null || s.length() < 2) {
      return true;
    }
    char[] arr = s.toCharArray();
    int lP = 0, rP = s.length() - 1;
    while (lP <= rP) {
      // 检查LP指向的是否是字母或者数字，否则向前移动，但前提是不能索引越界，如果已经索引越界，则说明剩下的没有数字或者字母，那么返回true
      while (isNumOrLetter(arr[lP])) {
        if (++lP >= arr.length) {
          return true;
        }
      }

      // 检查RP指向的是否是字母或者数字，否则向后移动
      while (isNumOrLetter(arr[rP])) {
        rP--;
      }

      // 如果字符相等 或者 (两个字母均不是数字 & 两个字符相差32)
      // 如果匹配，左指针向右移动，右指针向左移动
      if (arr[lP] == arr[rP]
          || (Math.min(arr[lP], arr[rP]) > 57 && Math.abs(arr[lP] - arr[rP]) == 32)) {
        lP++;
        rP--;
        continue;
      }
      return false;
    }
    return true;
  }

  /** 判断是否是数字或者字母 */
  private boolean isNumOrLetter(char c) {
    if ((int) c >= 48 && (int) c <= 57) {
      return false;
    }
    return ((int) c < 65 || (int) c > 90) && ((int) c < 97 || (int) c > 122);
  }
}
