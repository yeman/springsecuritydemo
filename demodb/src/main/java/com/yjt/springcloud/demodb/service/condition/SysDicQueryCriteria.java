package com.yjt.springcloud.demodb.service.condition;

import com.yjt.springcloud.demodb.anotation.Query;
import lombok.Data;

/**
 * TODO
 * ClassName: SysDicQueryCriteria
 * Date: 2019-09-23 23:30
 * author Administrator
 * version V1.0
 */
@Data
public class SysDicQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String dicType;

    @Query(type = Query.Type.INNER_LIKE)
    private String dicTypeName;

    @Query(type = Query.Type.EQUAL)
    private String enable;

}
