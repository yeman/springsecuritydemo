package com.yjt.springcloud.demo01.validatecode.generator;

import com.yjt.springcloud.demo01.config.properties.SecurityProperties;
import com.yjt.springcloud.demo01.validatecode.CustomImageCaptcha;
import com.yjt.springcloud.demo01.validatecode.bean.ValidateCode;
import com.yjt.springcloud.demo01.validatecode.exception.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @author YM
 * @version V1.0
 * @className ImageCodeGenerator
 * @description 短信验证码生成器
 * @date 2021-06-02 15:24
 * @since 1.0
 **/
@Slf4j
@Component(value = "smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Value("${captcha.wordSize:5}")
    private int wordSize;

    @Value("${captcha.expireTime:50}")
    private int validateCodeExpire;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(wordSize,validateCodeExpire);
        return new ValidateCode(code,validateCodeExpire);
    }
}
