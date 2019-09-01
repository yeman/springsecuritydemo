package com.yjt.springcloud.demodb.repository;

import com.yjt.springcloud.demodb.entity.Role;
import com.yjt.springcloud.demodb.orm.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO
 * ClassName: RoleRepository
 * Date: 2019-08-31 21:32
 * author Administrator
 * version V1.0
 */
public interface RoleRepository extends BaseRepository<Role,Long> {
}
