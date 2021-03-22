package com.zhoutao123.article.dubbo.provider.impl;

import com.zhoutao123.article.dubbo.provider.Color;

public class ColorWrapper implements Color {

    private Color color;


    public ColorWrapper(Color color) {
        this.color = color;
    }


    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void printInfo() {
        System.out.println("Before。。。");
        color.printInfo();
        System.out.println("After。。。。");
    }
}
