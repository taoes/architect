package com.zhoutao123.java.se.collect.stroage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Peroperties 使用案例
 */
public class PropertiesExample {

    public static void main(String[] args) throws FileNotFoundException {
        Properties properties = new Properties();
        properties.put("name", "张三");
        properties.put("age", "123");
        properties.put("address", "安徽省芜湖市九华北路171号");
        properties.save(new FileOutputStream("/tmp/123.properties"), "123");
    }
}
