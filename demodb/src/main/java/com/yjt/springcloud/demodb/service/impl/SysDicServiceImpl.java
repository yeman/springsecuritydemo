package com.yjt.springcloud.demodb.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.yjt.springcloud.demodb.entity.SysDic;
import com.yjt.springcloud.demodb.entity.dto.SysDicVo;
import com.yjt.springcloud.demodb.enums.YesNoEnum;
import com.yjt.springcloud.demodb.repository.SysDicItemRepository;
import com.yjt.springcloud.demodb.repository.SysDicRepository;
import com.yjt.springcloud.demodb.service.SysDicService;
import com.yjt.springcloud.demodb.service.condition.SysDicQueryCriteria;
import com.yjt.springcloud.demodb.service.condition.SystemParameterQueryCriteria;
import com.yjt.springcloud.demodb.service.mapper.SysDicMapper;
import com.yjt.springcloud.demodb.util.QueryHelp;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * TODO
 * ClassName: SysDicServiceImpl
 * Date: 2019-09-23 23:32
 * author Administrator
 * version V1.0
 */
@Service
public class SysDicServiceImpl implements SysDicService {
    @Autowired
    private SysDicRepository sysDicRepository;
    @Autowired
    private SysDicItemRepository sysDicItemRepository;
    @Autowired
    private SysDicMapper sysDicMapper;

    @Override
    public SysDic add(SysDic sysDic) {
        sysDic.setId(IdWorker.getId());
        return  sysDicRepository.save(sysDic);
    }

    @Override
    public SysDic update(SysDic sysDic) {
        return  sysDicRepository.update(sysDic);
    }

    @Override
    public SysDic findById(Long id) {
        return sysDicRepository.findById(id).get();
    }

    @Override
    public SysDic updateStateById(Map param) {
        Long id = MapUtils.getLong(param,"id");
        String enable = MapUtils.getString(param,"enable");
        SysDic sysDic = sysDicRepository.getOne(id);
        sysDic.setEnable(enable);
        return sysDicRepository.update(sysDic);
    }

    @Override
    public void deleteById(Long id) {
        sysDicRepository.deleteById(id);
    }

    @Override
    public Page page(SysDicQueryCriteria criteria, Pageable pageable) {
        Page<SysDic> page = sysDicRepository.findAll((root,criteriaQuery,criteriaBuilder)-> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return page.map(sysDicMapper::toDto);
    }

    @Override
    public List<SysDicVo> typeList() {
        return sysDicMapper.toDto(sysDicRepository.findByEnableOrderByCreateTime(YesNoEnum.YES.getKey()));
    }

    @Override
    public void deleteByIds(List<Long> ids) {
         ids.stream().forEach(this::deleteById);
    }
}
