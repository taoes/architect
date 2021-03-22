package com.zhoutao123.article.dubbo.provider.impl;

import com.zhoutao123.article.dubbo.provider.Color;

public class RedColor implements Color {
    @Override
    public void printInfo() {
        System.out.println("红色");
    }
}
