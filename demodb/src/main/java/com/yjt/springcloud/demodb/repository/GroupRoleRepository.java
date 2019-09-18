package com.yjt.springcloud.demodb.repository;

import com.yjt.springcloud.demodb.entity.Group;
import com.yjt.springcloud.demodb.entity.GroupRole;
import com.yjt.springcloud.demodb.orm.BaseRepository;
import com.yjt.springcloud.demodb.service.dto.GroupRoleQueryCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * 组角色
 * ClassName: GroupRoleRepository
 * Date: 2019-09-01 1:23
 * author Administrator
 * version V1.0
 */
public interface GroupRoleRepository extends BaseRepository<GroupRole,Long> {

    List<GroupRole> findByGroup(Group group);

    @Modifying
    @Query("delete from GroupRole g where g.group.groupId=?1")
    void deleteByGroup(Long groupId);
}