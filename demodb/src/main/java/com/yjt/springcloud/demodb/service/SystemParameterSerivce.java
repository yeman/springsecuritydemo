package com.yjt.springcloud.demodb.service;

import com.yjt.springcloud.demodb.entity.SystemParameter;
import com.yjt.springcloud.demodb.service.condition.SystemParameterQueryCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * 系统参数
 * ClassName: SystemParameterSerivce
 * Date: 2019-09-01 1:17
 * author Administrator
 * version V1.0
 */
public interface SystemParameterSerivce {

    SystemParameter add(SystemParameter systemParameter);

    SystemParameter update(SystemParameter systemParameter);

    SystemParameter findById(Long id);

    SystemParameter updateStateById(Map param);

    void deleteById(Long id);

    Page page(SystemParameterQueryCriteria criteria, Pageable pageable);
}
