package com.yjt.springcloud.demo01.validatecode.processor;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @className ValidateCodeProcessor
 * @description 验证码处理器
 * @author YM
 * @date 2021-05-27 11:49
 * @version V1.0
 * @since 1.0
 **/
public interface ValidateCodeProcessor {

    public void createCode(ServletWebRequest request);

    public void validateCode(ServletWebRequest servletWebRequest);
}
