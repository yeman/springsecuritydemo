package com.yjt.springcloud.demodb.service.dto;

import com.yjt.springcloud.demodb.anotation.Query;
import com.yjt.springcloud.demodb.entity.Group;
import lombok.Data;

/**
 * 组角色查询
 * ClassName: GroupRoleQueryCriteria
 * Date: 2019-09-16 21:38
 * author Administrator
 * version V1.0
 */
@Data
public class GroupRoleQueryCriteria {


    @Query(propName="groupId",joinName = "group")
    private Long groupId;

    @Query(propName="roleName",joinName = "role",type = Query.Type.INNER_LIKE)
    private String roleName;

    @Query(propName="roleCode",joinName = "role",type = Query.Type.INNER_LIKE)
    private String roleCode;

    @Query(propName = "state",joinName = "group")
    private String state;
}
