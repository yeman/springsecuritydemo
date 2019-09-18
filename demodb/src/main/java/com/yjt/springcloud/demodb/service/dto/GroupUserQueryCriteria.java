package com.yjt.springcloud.demodb.service.dto;

import com.yjt.springcloud.demodb.anotation.Query;
import lombok.Data;

/**
 * 组角色查询
 * ClassName: GroupRoleQueryCriteria
 * Date: 2019-09-16 21:38
 * author Administrator
 * version V1.0
 */
@Data
public class GroupUserQueryCriteria {


    @Query(propName="groupId",joinName = "group")
    private Long groupId;

    @Query(propName="userName",joinName = "user",type = Query.Type.INNER_LIKE)
    private String userName;

    @Query(propName="nickName",joinName = "user",type = Query.Type.INNER_LIKE)
    private String nickName;

    @Query(propName="mobile",joinName = "user",type = Query.Type.INNER_LIKE)
    private String mobile;

    @Query(propName="email",joinName = "user",type = Query.Type.INNER_LIKE)
    private String email;


}
