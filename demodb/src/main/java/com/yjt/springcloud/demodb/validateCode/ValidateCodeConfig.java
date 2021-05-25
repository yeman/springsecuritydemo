package com.yjt.springcloud.demodb.validateCode;

import com.yjt.springcloud.demodb.validateCode.image.ImageValidateCodeProperties;
import com.yjt.springcloud.demodb.validateCode.sms.SmsValidateCodeProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 * ClassName: ValidateCodeConfig
 * Date: 2019-10-22 22:30
 * author Administrator
 * version V1.0
 */
@Data
@ConfigurationProperties(prefix = "captcha")
public class ValidateCodeConfig {

    private String enable;

    /**
     * 验证码类型 短信SMS,图片验证 IMAGE
     */
    private String type;

    /**
     * 验证码有效期
     */
    private Long expiration;

    private ImageValidateCodeProperties imageValidateCodeProperties;

    private SmsValidateCodeProperties smsValidateCodeProperties;


}
