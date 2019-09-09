package com.yjt.springcloud.demodb.service;

import com.yjt.springcloud.demodb.entity.Role;

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

    Role findById(Long roleId);

    Role updateStateById(Map param);

    void deleteById(Long roleId);
}
