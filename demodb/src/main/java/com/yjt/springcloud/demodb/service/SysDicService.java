package com.yjt.springcloud.demodb.service;

import com.yjt.springcloud.demodb.entity.SysDic;
import com.yjt.springcloud.demodb.entity.dto.SysDicVo;
import com.yjt.springcloud.demodb.service.condition.SysDicQueryCriteria;
import com.yjt.springcloud.demodb.service.condition.SystemParameterQueryCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * TODO
 * ClassName: SysDicService
 * Date: 2019-09-23 23:25
 * author Administrator
 * version V1.0
 */
public interface SysDicService {

    SysDic add(SysDic sysDic);

    SysDic update(SysDic sysDic);

    SysDic findById(Long id);

    SysDic updateStateById(Map param);

    void deleteById(Long id);

    Page page(SysDicQueryCriteria criteria, Pageable pageable);

    List<SysDicVo> typeList();

    void deleteByIds(List<Long> toJavaList);
}
