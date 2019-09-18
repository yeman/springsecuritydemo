package com.yjt.springcloud.demodb.entity.dto;

import lombok.Data;

/**
 * TODO
 * ClassName: GroupRoleVo
 * Date: 2019-09-18 21:38
 * author Administrator
 * version V1.0
 */
@Data
public class GroupRoleVo {

    private Long id;
    private String groupName;
    private Long roleId;
    private String roleCode;
    private String roleName;
}
