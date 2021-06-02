package com.yjt.springcloud.demo01.validatecode.generator;

import com.yjt.springcloud.demo01.validatecode.CustomImageCaptcha;
import com.yjt.springcloud.demo01.validatecode.bean.ValidateCode;
import com.yjt.springcloud.demo01.validatecode.exception.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @className ImageCodeGenerator
 * @description TODO
 * @author YM
 * @date 2021-06-02 15:24
 * @version V1.0
 * @since 1.0
 **/
@Slf4j
@Component(value = "imageCodeGenerator")
public class ImageCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private CustomImageCaptcha captcha;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ValidateCode generate(ServletWebRequest request){
        ValidateCode validateCode = null;
        //渲染
        try {
            captcha.render();
        } catch (IOException e) {
           throw new ValidateCodeException("验证码生成失败");
        }
        String captchaId = request.getSessionId();
        String code = stringRedisTemplate.opsForValue().get(captcha.VALIDATE_CODE_CACHE_KEY + captchaId);
        if(StringUtils.isNotBlank(code)){
            log.debug("验证码有效期{}",captcha.getValidateCodeExpire());
            validateCode = new ValidateCode(code,captcha.getValidateCodeExpire());
        }
        return validateCode;
    }
}
