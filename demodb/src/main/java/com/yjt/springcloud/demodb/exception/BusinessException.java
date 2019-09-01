package com.yjt.springcloud.demodb.exception;

/**
 * 自定义业务异常
 * ClassName: BusinessException
 * Date: 2019-08-31 23:20
 * author Administrator
 * version V1.0
 */
public class BusinessException extends RuntimeException {

    BusinessException(){
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
