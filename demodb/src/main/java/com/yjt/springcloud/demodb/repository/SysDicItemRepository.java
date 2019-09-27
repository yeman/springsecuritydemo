package com.yjt.springcloud.demodb.repository;

import com.yjt.springcloud.demodb.entity.SysDic;
import com.yjt.springcloud.demodb.entity.SysDicItem;
import com.yjt.springcloud.demodb.orm.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 系统字典
 * ClassName: SysDicItemRepository
 * Date: 2019-08-31 21:45
 * author Administrator
 * version V1.0
 */
public interface SysDicItemRepository extends BaseRepository<SysDicItem,Long> {
    @Modifying
    @Query("delete from SysDicItem d where d.sysDic = ?1")
    void deleteBySysDic(SysDic dic);
}
