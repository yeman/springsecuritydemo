package com.yjt.springcloud.demodb.entity.dto;

import lombok.Data;

/**
 * TODO
 * ClassName: UserRoleVo
 * Date: 2019-09-19 23:52
 * author Administrator
 * version V1.0
 */
@Data
public class UserRoleVo {
    private Long id;
    private Long userId;
    private String userName;
    private String orgName;
    private String nickName;
    private String sex;
    private String mobile;
    private Long roleId;
    private String roleCode;
    private String roleName;
}
