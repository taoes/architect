package com.zhoutao123.article.dubbo.model;

import java.io.Serializable;


public class People implements Serializable {

    String name;

    int age;

    public People(String name, int age) {
        this.name = name + age;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
