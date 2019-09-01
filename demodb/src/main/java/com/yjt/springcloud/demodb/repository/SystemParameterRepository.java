package com.yjt.springcloud.demodb.repository;

import com.yjt.springcloud.demodb.entity.SystemParameter;
import com.yjt.springcloud.demodb.orm.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 系统参数
 * ClassName: SystemParametrRepository
 * Date: 2019-08-31 21:43
 * author Administrator
 * version V1.0
 */
public interface SystemParameterRepository extends BaseRepository<SystemParameter,Long> {
}
