package com.yjt.springcloud.demodb.enums;

import lombok.Data;
import lombok.Getter;

/**
 * 是否枚举
 */
@Getter
public enum YesNoEnum {

    YES("Y", "是"),
    NO("N", "否");

    private String key;
    private String value;

    YesNoEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

}
