package com.zhoutao123.article.dubbo.provider;

import com.alibaba.dubbo.config.spring.schema.DubboNamespaceHandler;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@ImportResource({"classpath:dubbo.xml"})
public class ProviderApplication {


    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);


        DubboNamespaceHandler handler;
        BeanDefinitionParser parser;

        CountDownLatch latch;
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });

//        Color aDefault = ExtensionLoader.getExtensionLoader(Color.class).getExtension("default");
//        aDefault.printInfo();
    }

}
