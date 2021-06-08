package com.yjt.springcloud.demo01.validatecode.generator;

import com.yjt.springcloud.demo01.validatecode.CustomImageCaptcha;
import com.yjt.springcloud.demo01.validatecode.bean.ValidateCode;
import com.yjt.springcloud.demo01.validatecode.exception.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @author YM
 * @version V1.0
 * @className ImageCodeGenerator
 * @description TODO
 * @date 2021-06-02 15:24
 * @since 1.0
 **/
@Slf4j
@Component(value = "imageValidateCodeGenerator")
public class ImageCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private CustomImageCaptcha captcha;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        ValidateCode validateCode = null;
        //渲染
        try {
            validateCode = captcha.render();
        } catch (IOException e) {
            throw new ValidateCodeException("验证码生成失败");
        }
        return validateCode;
    }
}
