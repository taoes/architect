package com.zhoutao123.article.dubbo.consumer;


import com.zhoutao123.article.dubbo.exception.CustomerDubboException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class CommonExceptionHandler {


    @ResponseBody
    @ExceptionHandler(CustomerDubboException.class)
    public String handle(CustomerDubboException e) {
        return e.getMessage();
    }
}
