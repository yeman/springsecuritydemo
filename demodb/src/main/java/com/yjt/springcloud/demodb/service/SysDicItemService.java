package com.yjt.springcloud.demodb.service;

import com.yjt.springcloud.demodb.entity.SysDicItem;
import com.yjt.springcloud.demodb.service.condition.SysDicItemQueryCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * TODO
 * ClassName: SysDicItemService
 * Date: 2019-09-23 23:40
 * author Administrator
 * version V1.0
 */
public interface SysDicItemService {
    Object findById(Long id);

    Object updateStateById(Map param);

    void deleteById(Long id);

    Page page(SysDicItemQueryCriteria criteria, Pageable pageable);

    Object add(SysDicItem sysDicItem);

    Object update(SysDicItem sysDicItem);

    void deleteByIds(List<Long> toJavaList);
}
