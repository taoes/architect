package com.zhoutao123.leetcode.simple;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Q020: 有效的括号
 *
 * <p>执行用时： 2 ms , 在所有 Java 提交中击败了 76.16% 的用户
 *
 * <p>内存消耗： 36.7 MB , 在所有 Java 提交中击败了 37.97% 的用户
 *
 * @apiNote https://leetcode-cn.com/problems/palindrome-number
 */
public class Q020_ValidParentheses {

  public static void main(String[] args) {
    boolean result = new Q020_ValidParentheses().isValid("()[]({[]})");
    System.out.println(result);
  }

  public boolean isValid(String s) {
    List<Character> leftChar = Arrays.asList('[', '{', '(');
    List<Character> rightChar = Arrays.asList(']', '}', ')');

    Stack<Integer> stack = new Stack<>();
    char[] chars = s.toCharArray();
    for (Character aChar : chars) {
      int leftCharIndex = leftChar.indexOf(aChar);
      if (leftCharIndex != -1) {
        stack.push(leftCharIndex);
        continue;
      }

      if (stack.isEmpty()) {
        return false;
      }
      leftCharIndex = stack.pop();
      int rightCharIndex = rightChar.indexOf(aChar);
      if (leftCharIndex != rightCharIndex) {
        return false;
      }
    }
    return stack.isEmpty();
  }
}
