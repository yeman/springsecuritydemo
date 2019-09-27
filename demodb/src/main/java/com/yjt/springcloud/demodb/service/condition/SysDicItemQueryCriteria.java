package com.yjt.springcloud.demodb.service.condition;

import com.yjt.springcloud.demodb.anotation.Query;
import lombok.Data;

/**
 * TODO
 * ClassName: SysDicItemQueryCriteria
 * Date: 2019-09-23 23:30
 * author Administrator
 * version V1.0
 */
@Data
public class SysDicItemQueryCriteria {

    @Query(propName = "id",joinName = "sysDic")
    private Long dicId;

    @Query(type = Query.Type.INNER_LIKE)
    private String labelCode;

    @Query(type = Query.Type.INNER_LIKE)
    private String labelName;

    @Query(type = Query.Type.EQUAL)
    private String enable;

    @Query(type = Query.Type.INNER_LIKE)
    private String description;

}
