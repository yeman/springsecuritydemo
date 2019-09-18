package com.yjt.springcloud.demodb.entity.condition;

import com.yjt.springcloud.demodb.anotation.Query;
import lombok.Data;

import java.util.Set;

/**
 * 机构查询条件
 * ClassName: OrgCondition
 * Date: 2019-09-11 21:40
 * author Administrator
 * version V1.0
 */
@Data
public class OrgCondition {

    @Query(type = Query.Type.IN, propName="id")
    private Set<Long> ids;

    @Query(type = Query.Type.INNER_LIKE)
    private String orgName;

    @Query
    private String enabled;

    @Query
    private Long parentId;
}
