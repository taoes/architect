package com.zhoutao123.struct;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯算法求子集
 */
public class SetDemo {
    public static void main(String[] args) {
        int[] numbers = new int[]{1};
        List<List<Integer>> exec = exec(numbers.length, numbers);
        System.out.println(exec);

    }

    public static List<List<Integer>> exec(int k, int[] numbers) {

        if (k == 0) {
            List<Integer> objects = new ArrayList<>();
            ArrayList<List<Integer>> lists = new ArrayList<>();
            lists.add(objects);
            return lists;
        }
        List<List<Integer>> s = exec(k - 1, numbers);
        for (int i = 0; i < s.size(); i++) {
            s.get(i).add(k);
        }
        ArrayList<Integer> newList = new ArrayList<>();
        newList.add(k);
        s.add(newList);
        return s;
    }
}
