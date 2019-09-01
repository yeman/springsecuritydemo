package com.yjt.springcloud.demodb.repository;

import com.yjt.springcloud.demodb.entity.RolePermission;
import com.yjt.springcloud.demodb.orm.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 角色权限
 * ClassName: RolePermissionRepository
 * Date: 2019-09-01 1:21
 * author Administrator
 * version V1.0
 */
public interface RolePermissionRepository extends BaseRepository<RolePermission,Long> {
}
