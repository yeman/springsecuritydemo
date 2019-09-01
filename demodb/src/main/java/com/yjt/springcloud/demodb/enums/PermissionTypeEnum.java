package com.yjt.springcloud.demodb.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum PermissionTypeEnum {
    MODULE("module", "模块"),
    MENU("menu", "菜单"),
    BUTTON("button", "按钮"),
    OTHER("other", "其它");

    private String key;
    private String value;

    PermissionTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
