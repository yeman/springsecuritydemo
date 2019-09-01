package com.yjt.springcloud.demodb.repository;

import com.yjt.springcloud.demodb.entity.UserGroup;
import com.yjt.springcloud.demodb.orm.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户组
 * ClassName: UserGroupRepository
 * Date: 2019-09-01 1:22
 * author Administrator
 * version V1.0
 */
public interface UserGroupRepository extends BaseRepository<UserGroup,Long> {
}
