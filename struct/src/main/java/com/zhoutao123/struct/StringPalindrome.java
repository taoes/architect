package com.zhoutao123.struct;

/**
 * 潘墩字符串是否是回文串
 */
public class StringPalindrome {

    public static void main(String[] args) {
        String name = "-1";
        int length = name.length();
        char[] chars = name.toCharArray();
        for (int i = 0; i < (length >> 1); i++) {
            if (chars[i] != chars[length - i - 1]) {
                System.out.println("非回文串");
                return;
            }
        }
        System.out.println("是回文串");
    }
}
