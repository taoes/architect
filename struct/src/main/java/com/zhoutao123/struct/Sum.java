package com.zhoutao123.struct;

import java.util.HashMap;
import java.util.Map;


/**
 * 两数之和
 *
 * @apiNote 时间复杂度 O(n)
 */
public class Sum {
    public static void main(String[] args) {
        int[] numbers = new int[]{1, 3, 5, 6, 8, 9, 0, 2};
        int target = 8;

        Map<Integer, Integer> s = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (s.containsKey(numbers[i])) {
                System.out.println("i=" + s.get(numbers[i]) + "  j=" + i);
            } else {
                s.put(target - numbers[i], i);
            }
        }
    }
}
