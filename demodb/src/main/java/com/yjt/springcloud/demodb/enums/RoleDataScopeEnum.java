package com.yjt.springcloud.demodb.enums;

import lombok.Data;
import lombok.Getter;

/**
 * TODO
 * ClassName: RoleDataScopeEnum
 * Date: 2019-09-05 20:22
 * author Administrator
 * version V1.0
 */
@Getter
public enum  RoleDataScopeEnum {

    ALL("ALL","全部数据权限"),
    DEPT("DEPT","部门及其以下"),
    CURRENTDEPT("CURRENTDEPT","本部门"),
    USER("USER","本人数据权限"),
    CUSTOMER("CUSTOMER","自定义数据权限");
    private String type;
    private String desc;

    RoleDataScopeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
