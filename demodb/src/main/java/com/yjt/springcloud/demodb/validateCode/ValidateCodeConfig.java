package com.yjt.springcloud.demodb.validateCode;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 * ClassName: ValidateCodeConfig
 * Date: 2019-10-22 22:30
 * author Administrator
 * version V1.0
 */
@Configuration
@Data
@ConditionalOnProperty(prefix="sys.captcha")
public class ValidateCodeConfig {

    private String enable;
    /**
     * 验证码类型 短信SMS,图片验证
     */
    private String type;

    /**
     * 验证码复杂度
     */
    private String complexity;

    private int width;

    private int height;

    /**
     * 图片验证码位数
     */
    private int textcount;

    /**
     * 数学计算验证码位数
     */
    private int mathcount;

    /**
     * 验证码有效期
     */
    private Long expiration;



}
