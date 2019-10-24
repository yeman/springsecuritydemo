package com.yjt.springcloud.demodb.validateCode;

import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * TODO
 * ClassName: SmsValidateCode
 * Date: 2019-10-23 22:37
 * author Administrator
 * version V1.0
 */
@Data
public class SmsValidateCode extends ValidateCode {
    private String mobile;

    public SmsValidateCode(String mobile, String code, Long expireTime) {
        super(code, expireTime);
        this.mobile = mobile;

    }
}
