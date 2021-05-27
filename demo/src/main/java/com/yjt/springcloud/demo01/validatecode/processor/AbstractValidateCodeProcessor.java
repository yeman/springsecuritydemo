package com.yjt.springcloud.demo01.validatecode.processor;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @className AbstractValidateCodeProcessor
 * @description TODO
 * @author YM
 * @date 2021-05-27 11:51
 * @version V1.0
 * @since 1.0
 **/
public class AbstractValidateCodeProcessor implements ValidateCodeProcessor {

    @Override
    public void createCode(ServletWebRequest request) {

    }

    @Override
    public void validateCode(ServletWebRequest servletWebRequest) {

    }
}
