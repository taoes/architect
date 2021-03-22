package com.zhoutao123.article.dubbo.exception;

/**
 * 自定义的Dubbo异常
 */
public class CustomerDubboException extends RuntimeException {

    public CustomerDubboException() {
    }

    public CustomerDubboException(String message) {
        super(message);
    }

    public CustomerDubboException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerDubboException(Throwable cause) {
        super(cause);
    }

    public CustomerDubboException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
