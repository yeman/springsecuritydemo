package com.yjt.springcloud.demodb.repository;

import com.yjt.springcloud.demodb.entity.SysDic;
import com.yjt.springcloud.demodb.orm.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 系统字典
 * ClassName: SysDicRepository
 * Date: 2019-08-31 21:44
 * author Administrator
 * version V1.0
 */
public interface SysDicRepository extends BaseRepository<SysDic,Long> {
    List<SysDic> findByEnableOrderByCreateTime(String enable);
}
