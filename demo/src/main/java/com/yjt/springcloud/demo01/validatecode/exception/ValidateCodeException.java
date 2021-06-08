package com.yjt.springcloud.demo01.validatecode.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @className ValidateCodeException
 * @description 验证码过期异常
 * @author YM
 * @date 2021-05-24 17:35
 * @version V1.0
 * @since 1.0
 **/
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String message) {
        super(message);
    }
}
