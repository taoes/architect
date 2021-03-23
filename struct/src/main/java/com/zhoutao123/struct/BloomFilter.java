package com.zhoutao123.struct;

import java.util.Arrays;

/**
 * 实现一个简单的布隆过滤器
 */
public class BloomFilter {

    private final byte[] bytes;

    private final int size;

    public BloomFilter(int size) {
        this.bytes = new byte[size];
        this.size = size;
        Arrays.fill(bytes, (byte) 0);
    }

    void add(Object object) {
        int[] hash = hash(object);
        for (int hashValue : hash) {
            this.bytes[hashValue] = (byte) 1;
        }
    }

    boolean exist(Object obj) {
        int[] hash = hash(obj);
        for (int hashValue : hash) {
            boolean exist = this.bytes[hashValue] == 1;
            if (!exist) {
                return false;
            }
        }
        return true;
    }


    int[] hash(Object object) {

        return new int[]{object.hashCode() % size};
    }

    public static void main(String[] args) {
        BloomFilter filter = new BloomFilter(2000);
        for (int i = 0; i < 40; i++) {
            filter.add(String.valueOf(i));
        }

        // 判断是否存在
        System.out.println(filter.exist("43"));

        for (int i = 0; i < 40; i++) {
            assert filter.exist(String.valueOf(i));
        }


    }
}
