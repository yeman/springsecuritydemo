package com.yjt.springcloud.demodb.enums;


import lombok.Getter;

/**
 * 性别枚举
 */
@Getter
public enum SexEnum {

    MALE("M", "男"),
    FEMALE("F", "女");


    private String key;
    private String value;

    SexEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

}
