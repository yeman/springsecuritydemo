package com.yjt.springcloud.demodb.enums;

import lombok.Getter;

/**
 * 是否枚举
 */
@Getter
public enum ValidateTypeEnum {

    IMAGE("IMAGE", "图片验证码"),
    SMS("SMS", "短信验证码");

    private String key;
    private String value;

    ValidateTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

}
