package com.yjt.springcloud.demodb.repository;

import com.yjt.springcloud.demodb.entity.Role;
import com.yjt.springcloud.demodb.entity.UserRole;
import com.yjt.springcloud.demodb.orm.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户角色
 * ClassName: UserRoleRepository
 * Date: 2019-09-01 1:20
 * author Administrator
 * version V1.0
 */
public interface UserRoleRepository extends BaseRepository<UserRole,Long> {

    List<UserRole> findByRole(Role role);
}
