package com.zhoutao123.leetcode.simple;

/**
 * Q014: 最长公共前缀
 *
 * <p>执行用时： 1 ms , 在所有 Java 提交中击败了 84.05% 的用户
 *
 * <p>内存消耗： 36.5 MB , 在所有 Java 提交中击败了 71.19% 的用户
 *
 * @author https://leetcode-cn.com/problems/longest-common-prefix/
 */
public class Q014_LongestCommonPrefix {

  public static void main(String[] args) {
    String[] strs = new String[] {"123456", "123", "1234", "1243a", "12349"};
    String result = new Q014_LongestCommonPrefix().longestCommonPrefix(strs);
    System.out.println(result);
  }

  public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) {
      return "";
    }
    if (strs.length == 1) {
      return strs[0];
    }
    // 按长度排序
    int minIndex = 0;
    for (int i = 1; i < strs.length; i++) {
      if (strs[i].length() < strs[minIndex].length()) {
        minIndex = i;
      }
    }

    int minLength = strs[minIndex].length();
    for (int i = 0; i < minLength; i++) {
      char c = strs[minIndex].charAt(i);
      for (int j = 0; j < strs.length; j++) {
        if (j == minIndex) {
          continue;
        }
        if (strs[j].charAt(i) != c) {
          if (i == 0) {
            return "";
          } else return strs[minIndex].substring(0, i);
        }
      }
    }
    return strs[minIndex];
  }
}
