package com.yjt.springcloud.demodb.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.yjt.springcloud.demodb.entity.SystemParameter;
import com.yjt.springcloud.demodb.repository.SystemParameterRepository;
import com.yjt.springcloud.demodb.service.SystemParameterSerivce;
import com.yjt.springcloud.demodb.service.condition.SystemParameterQueryCriteria;
import com.yjt.springcloud.demodb.util.QueryHelp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * TODO
 * ClassName: SystemParameterSerivceImpl
 * Date: 2019-09-01 1:18
 * author Administrator
 * version V1.0
 */
@Slf4j
@Service
public class SystemParameterSerivceImpl implements SystemParameterSerivce {
    @Autowired
    private SystemParameterRepository systemParameterRepository;


    @Override
    public SystemParameter add(SystemParameter systemParameter) {
        systemParameter.setId(IdWorker.getId());
        systemParameterRepository.save(systemParameter);
        return systemParameter;
    }

    @Override
    public SystemParameter update(SystemParameter systemParameter) {
        systemParameterRepository.update(systemParameter);
        return systemParameter;
    }

    @Override
    public SystemParameter findById(Long id) {
        return systemParameterRepository.findById(id).get();
    }

    @Override
    public SystemParameter updateStateById(Map param) {
        Long id = MapUtils.getLong(param,"id");
        String enable = MapUtils.getString(param, "enable");
        SystemParameter systemParameter = systemParameterRepository.findById(id).get();
        systemParameter.setEnable(enable);
        systemParameterRepository.update(systemParameter);
        return systemParameter;
    }

    @Override
    public void deleteById(Long id) {
        systemParameterRepository.deleteById(id);
    }

    @Override
    public Page page(SystemParameterQueryCriteria criteria, Pageable pageable) {
        return systemParameterRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
    }
}
