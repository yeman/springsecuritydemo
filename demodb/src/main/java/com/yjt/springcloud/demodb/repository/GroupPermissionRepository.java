package com.yjt.springcloud.demodb.repository;

import com.yjt.springcloud.demodb.entity.GroupPermission;
import com.yjt.springcloud.demodb.orm.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 组权限
 * ClassName: GroupPermissionRepository
 * Date: 2019-09-01 1:23
 * author Administrator
 * version V1.0
 */
public interface GroupPermissionRepository extends BaseRepository<GroupPermission,Long> {
}
