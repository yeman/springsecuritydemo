package com.yjt.springcloud.demodb.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.yjt.springcloud.demodb.entity.SysDicItem;
import com.yjt.springcloud.demodb.repository.SysDicItemRepository;
import com.yjt.springcloud.demodb.service.SysDicItemService;
import com.yjt.springcloud.demodb.service.condition.SysDicItemQueryCriteria;
import com.yjt.springcloud.demodb.service.mapper.SysDicItemMapper;
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
 * ClassName: SysDicItemServiceImpl
 * Date: 2019-09-23 23:56
 * author Administrator
 * version V1.0
 */
@Service
public class SysDicItemServiceImpl implements SysDicItemService {
    @Autowired
    private SysDicItemRepository sysDicItemRepository;
    @Autowired
    private SysDicItemMapper sysDicItemMapper;

    @Override
    public SysDicItem add(SysDicItem sysDicItem) {
        sysDicItem.setId(IdWorker.getId());
        return sysDicItemRepository.save(sysDicItem);
    }

    @Override
    public SysDicItem update(SysDicItem sysDicItem) {
        return sysDicItemRepository.update(sysDicItem);
    }

    @Override
    public SysDicItem findById(Long id) {
        return sysDicItemRepository.findById(id).get();
    }

    @Override
    public SysDicItem updateStateById(Map param) {
        Long id = MapUtils.getLong(param,"id");
        String enable = MapUtils.getString(param,"enable");
        SysDicItem sysDicItem = sysDicItemRepository.findById(id).get();
        sysDicItem.setEnable(enable);
        sysDicItemRepository.update(sysDicItem);
        return sysDicItem;
    }

    @Override
    public void deleteById(Long id) {
         sysDicItemRepository.deleteById(id);
    }


    @Override
    public void deleteByIds(List<Long> ids) {
        ids.stream().forEach(this::deleteById);
    }
    @Override
    public Page page(SysDicItemQueryCriteria criteria, Pageable pageable) {
         Page<SysDicItem> page =  sysDicItemRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
         return page.map(sysDicItemMapper::toDto);
    }

}
