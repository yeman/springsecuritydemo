package com.yjt.springcloud.demodb.repository;

import com.yjt.springcloud.demodb.entity.Role;
import com.yjt.springcloud.demodb.orm.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * 角色
 * ClassName: RoleRepository
 * Date: 2019-08-31 21:32
 * author Administrator
 * version V1.0
 */
public interface RoleRepository extends BaseRepository<Role,Long> {

    List<Role> findByParentIdOrderBySortOrder(Long id);

    Optional<Role> findByParentIdIsNullOrderBySortOrder();

}
