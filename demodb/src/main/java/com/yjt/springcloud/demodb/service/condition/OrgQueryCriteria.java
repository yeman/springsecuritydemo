package com.yjt.springcloud.demodb.service.condition;

import com.yjt.springcloud.demodb.anotation.Query;
import lombok.Data;

import java.util.Set;

/**
 * 机构查询条件
 * ClassName: OrgQueryCriteria
 * Date: 2019-09-11 21:40
 * author Administrator
 * version V1.0
 */
@Data
public class OrgQueryCriteria {

    @Query(type = Query.Type.IN, propName="id")
    private Set<Long> ids;

    @Query(type = Query.Type.INNER_LIKE)
    private String orgName;

    @Query
    private String enabled;

    @Query
    private Long parentId;
}
