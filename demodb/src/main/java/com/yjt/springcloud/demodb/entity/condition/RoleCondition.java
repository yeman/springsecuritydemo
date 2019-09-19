package com.yjt.springcloud.demodb.entity.condition;

import com.yjt.springcloud.demodb.anotation.Query;
import lombok.Data;

import java.util.Date;

/**
 * 角色查询条件
 * ClassName: RoleCondition
 * Date: 2019-09-11 21:42
 * author Administrator
 * version V1.0
 */
@Data
public class RoleCondition {

    @Query(type = Query.Type.INNER_LIKE)
    private String roleName;

    @Query
    private String roleCode;

    @Query
    private String state;

    @Query(type = Query.Type.GREATER_THAN)
    private Date startDate;

    @Query(type = Query.Type.LESS_THAN)
    private Date endDate;

}
