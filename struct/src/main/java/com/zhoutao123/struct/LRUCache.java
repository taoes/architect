package com.zhoutao123.struct;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 使用LinkedHashMap实现LRU缓存算法
 */
public class LRUCache<K, V> {


    private final LinkedHashMap<K, V> map;

    public LRUCache(int maxSize) {
        this.map = new LinkedHashMap<K, V>(maxSize, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return this.size() > maxSize;
            }
        };
    }

    public synchronized void put(K k, V v) {
        map.put(k, v);
    }

    public synchronized V get(K k) {
        return map.get(k);
    }

    public synchronized void printLru() {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.print(entry.getKey() + "-" + entry.getValue());
            System.out.println(",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache<String, String> cache = new LRUCache<>(3);
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");


        cache.printLru();
        cache.get("1");
        cache.get("1");
        cache.get("1");
        cache.get("3");

        System.out.println("------------------------------------------------------------------------");
        cache.put("4", "4");
        cache.printLru();

    }
}
