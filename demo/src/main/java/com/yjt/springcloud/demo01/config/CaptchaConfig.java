package com.yjt.springcloud.demo01.config;

import com.octo.captcha.engine.image.gimpy.SimpleListImageCaptchaEngine;
import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.yjt.springcloud.demo01.validatecode.DefaultImageCaptcha;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @ClassName CaptchaConfig
 * @Description TODO
 * @Author: YM
 * @Version V1.0
 * @Since V1.0
 * @Date: 2019-09-18 16:11
 **/
@Configuration
public class CaptchaConfig {

   /*方式一 @Bean
    @Primary
    public CaptchaService imageCaptchaService() {
        return new DefaultManageableImageCaptchaService();
    }*/
   @Bean
   public DefaultImageCaptcha imageCaptcha(){
       return new DefaultImageCaptcha();
   }

   //方式二
   @Bean
    public SimpleListImageCaptchaEngine captchaEngine(){
        return new SimpleListImageCaptchaEngine();
    }
    @Bean
    public CaptchaService imageCaptchaService() {
        CaptchaService service = new DefaultManageableImageCaptchaService();
        ((DefaultManageableImageCaptchaService) service).setCaptchaEngine(captchaEngine());
        return service;
    }


}
