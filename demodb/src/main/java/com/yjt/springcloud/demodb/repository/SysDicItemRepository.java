package com.yjt.springcloud.demodb.repository;

import com.yjt.springcloud.demodb.entity.SysDicItem;
import com.yjt.springcloud.demodb.orm.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 系统字典
 * ClassName: SysDicItemRepository
 * Date: 2019-08-31 21:45
 * author Administrator
 * version V1.0
 */
public interface SysDicItemRepository extends BaseRepository<SysDicItem,Long> {
}
