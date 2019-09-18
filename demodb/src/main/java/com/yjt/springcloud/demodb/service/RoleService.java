package com.yjt.springcloud.demodb.service;

import com.yjt.springcloud.demodb.entity.Role;
import com.yjt.springcloud.demodb.entity.dto.RoleTreeVo;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 角色
 * ClassName: RoleService
 * Date: 2019-09-05 20:04
 * author Administrator
 * version V1.0
 */
public interface RoleService {
    Role add(Role role);

    Role update(Role role);

    Role findById(@NotNull Long roleId);

    Role updateStateById(Map param);

    void deleteById(@NotNull Long roleId);

    void assginUser(List<Long> userIds, Long roleId);

    RoleTreeVo tree(Map param);
}
