package com.yjt.springcloud.demodb.repository;

import com.yjt.springcloud.demodb.entity.Group;
import com.yjt.springcloud.demodb.orm.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 组
 * ClassName: GroupRepository
 * Date: 2019-08-31 21:40
 * author Administrator
 * version V1.0
 */
public interface GroupRepository extends BaseRepository<Group,Long> {
}
