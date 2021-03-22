package com.zhoutao123.article.dubbo.provider;


import com.alibaba.dubbo.common.extension.SPI;

@SPI
public interface Color {
    void printInfo();
}
