package com.yjt.springcloud.demodb.service.condition;

import com.yjt.springcloud.demodb.anotation.Query;
import lombok.Data;

/**
 * TODO
 * ClassName: UserRoleQueryCriteria
 * Date: 2019-09-19 23:39
 * author Administrator
 * version V1.0
 */
@Data
public class UserRoleQueryCriteria {

    @Query(propName="id",joinName = "role")
    private Long roleId;

    @Query(propName="userName",joinName = "user",type = Query.Type.INNER_LIKE)
    private String userName;

    @Query(propName="nickName",joinName = "user",type = Query.Type.INNER_LIKE)
    private String nickName;

    @Query(propName="mobile",joinName = "user",type = Query.Type.INNER_LIKE)
    private String mobile;

    @Query(propName="email",joinName = "user",type = Query.Type.INNER_LIKE)
    private String email;
}
