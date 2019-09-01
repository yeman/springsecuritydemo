package com.yjt.springcloud.demodb.repository;

import com.yjt.springcloud.demodb.entity.Permission;
import com.yjt.springcloud.demodb.orm.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 权限
 * ClassName: PermissionRepository
 * Date: 2019-08-31 21:35
 * author Administrator
 * version V1.0
 */
public interface PermissionRepository extends BaseRepository<Permission,Long> {
}
