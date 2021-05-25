package com.yjt.springcloud.demo01.validatecode;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 * ClassName: ValidateConfig
 * Date: 2019-10-22 22:30
 * author Administrator
 * version V1.0
 */
@Configuration
@Data
@ConditionalOnProperty(prefix="sys.captcha")
public class ValidateConfig {

    private String enable;
    /**
     * 验证码类型 短信SMS,图片验证 PIC
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



}
