package com.yjt.springcloud.demodb.repository;

import com.yjt.springcloud.demodb.entity.Role;
import com.yjt.springcloud.demodb.entity.RoleDataPermission;
import com.yjt.springcloud.demodb.orm.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 角色数据权限
 * ClassName: RoleDataPermissionRepository
 * Date: 2019-09-09 22:15
 * author Administrator
 * version V1.0
 */
public interface RoleDataPermissionRepository  extends BaseRepository<RoleDataPermission,Long> {
    @Modifying
    @Query("delete from RoleDataPermission r where r.role.id=?1")
    void deleteByRole(Long roleId);
}
