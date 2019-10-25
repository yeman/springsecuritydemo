package com.yjt.springcloud.demodb.validateCode.image;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO
 * ClassName: ImageValidateCodeProperties
 * Date: 2019-10-24 22:41
 * author Administrator
 * version V1.0
 */
@ConfigurationProperties(prefix = "captcha.type", value = "image")
@Data
public class ImageValidateCodeProperties {

    private int width;

    private int height;

    /**
     * 验证码复杂度
     */
    private String complexity;

    /**
     * 图片验证码位数
     */
    private int textcount;

    /**
     * 数学计算验证码位数
     */
    private int mathcount;
}
