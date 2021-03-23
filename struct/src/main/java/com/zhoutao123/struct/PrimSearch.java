package com.zhoutao123.struct;

import java.util.Arrays;

/**
 * 高效查询素数
 *
 * @apiNote 时间复杂度: O(n/2)
 */
public class PrimSearch {
    public static void main(String[] args) {
        int n = 10000;
        boolean[] primArray = new boolean[n];
        Arrays.fill(primArray, true);
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (primArray[i]) {
                for (int j = i * i; j < n; j += i) {
                    primArray[j] = false;
                }
            }
        }

        for (int i = 2; i < primArray.length; i++) {
            if (primArray[i]) {
                System.out.print(i + ",");
            }
        }
        System.out.println();
    }
}
