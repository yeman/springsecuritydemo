package com.yjt.springcloud.demodb.enums;

import lombok.Getter;

@Getter
public enum OpenTypeEnum {
    TAB("tab", "页签"),
    WINDOW("window", "菜单");

    private String openType;
    private String desc;

    OpenTypeEnum(String openType, String desc) {
        this.openType = openType;
        this.desc = desc;
    }
}
