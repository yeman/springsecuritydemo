package com.yjt.springcloud.demo01.validatecode.config;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.FunkyBackgroundGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.TwistedAndShearedRandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.CaptchaService;
import com.yjt.springcloud.demo01.validatecode.CaptchaProperties;
import com.yjt.springcloud.demo01.validatecode.DefaultCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

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

    @Autowired
    private CaptchaProperties captchaProperties;

    /*

    //方式一
     @Bean
     @Primary
     public CaptchaService imageCaptchaService() {
         return new DefaultManageableImageCaptchaService();
     }

    @Bean
    public DefaultImageCaptcha imageCaptcha() {
        return new DefaultImageCaptcha();
    }*/

    //方式二
    /*@Bean
    public SimpleListImageCaptchaEngine captchaEngine() {
        return new SimpleListImageCaptchaEngine();
    }

    @Bean
    public CaptchaService imageCaptchaService() {
        CaptchaService service = new DefaultManageableImageCaptchaService();
        ((DefaultManageableImageCaptchaService) service).setCaptchaEngine(captchaEngine());
        return service;
    }*/

    //方式三自定义

    public CaptchaEngine captchaEngine() {
        return new ListImageCaptchaEngine() {
            @Override
            protected void buildInitialFactories() {
                TextPaster textPaster = new RandomTextPaster(captchaProperties.getMinWordSize(), captchaProperties.getMaxWordSize(), Color.getColor(captchaProperties.getColor()));
                BackgroundGenerator backgroundGenerator = new FunkyBackgroundGenerator(captchaProperties.getWidth(), captchaProperties.getHeight());
                FontGenerator fontGenerator = new TwistedAndShearedRandomFontGenerator(captchaProperties.getMinFontSize(), captchaProperties.getMaxFontSize());
                WordToImage wordToImage = new ComposedWordToImage(fontGenerator, backgroundGenerator, textPaster);
                this.addFactory(new GimpyFactory(wordGenerator(), wordToImage));
            }
        };
    }

    @Bean
    public CaptchaService imageCaptchaService() {
        CaptchaService service = new DefaultCaptchaService();
        ((DefaultCaptchaService) service).setCaptchaEngine(captchaEngine());
        return service;
    }

   @Bean
    public WordGenerator wordGenerator(){
        WordGenerator wordGenerator = new RandomWordGenerator(captchaProperties.getSource());
        return wordGenerator;
    }

    @Bean
    WordToImage wordToImage(){
        TextPaster textPaster = new RandomTextPaster(captchaProperties.getMinWordSize(), captchaProperties.getMaxWordSize(), Color.getColor(captchaProperties.getColor()));
        BackgroundGenerator backgroundGenerator = new FunkyBackgroundGenerator(captchaProperties.getWidth(), captchaProperties.getHeight());
        FontGenerator fontGenerator = new TwistedAndShearedRandomFontGenerator(captchaProperties.getMinFontSize(), captchaProperties.getMaxFontSize());
        WordToImage wordToImage = new ComposedWordToImage(fontGenerator, backgroundGenerator, textPaster);
        return wordToImage;
    }



}
