package com.yjt.springcloud.demo01.validatecode;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * @className CaptchaProperties
 * @description 验证码配置类
 * @author YM
 * @date 2021-05-24 11:38
 * @version V1.0
 * @since 1.0
 **/
@Component
@Data
@ConfigurationProperties(prefix = "captcha")
public class CaptchaProperties {

    /**
     * 验证码数据源
     **/
    private String source;

    /**
     * 过期时间毫秒数
     **/
    private Long expireTime;

    /**
     * 验证码最小长度
     **/
    private int minWordSize;

    /**
     * 验证码最大长度
     **/
    private int maxWordSize;

    /**
     * 背景宽度
     **/
    private int width;

    /**
     * 背景高度
     **/
    private int height;


    /**
     * 最小字体大小
     **/
    private int minFontSize;

    /**
     * 最大字体大小
     **/
    private int maxFontSize;

    /**
     * 字体颜色
     **/
    private String color;

}
