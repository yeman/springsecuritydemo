package com.yjt.springcloud.demodb.service.condition;

import com.yjt.springcloud.demodb.anotation.Query;
import lombok.Data;

import java.util.Date;

/**
 * TODO
 * ClassName: SystemParameterQueryCriteria
 * Date: 2019-09-23 21:47
 * author Administrator
 * version V1.0
 */
@Data
public class SystemParameterQueryCriteria {


    @Query(type = Query.Type.INNER_LIKE)
    private String parameterName;

    @Query(type = Query.Type.INNER_LIKE)
    private String parameterKey;

    @Query(type = Query.Type.INNER_LIKE)
    private String parameterValue;

    @Query(type = Query.Type.EQUAL)
    private String enable;

    @Query(type = Query.Type.EQUAL)
    private String systemDefault;

    @Query(type = Query.Type.GREATER_THAN)
    private Date startDate;

    @Query(type = Query.Type.LESS_THAN)
    private Date endDate;



}
