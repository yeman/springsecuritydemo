package com.yjt.springcloud.demodb.service;

import com.yjt.springcloud.demodb.entity.Permission;
import com.yjt.springcloud.demodb.entity.dto.PermissionVo;

import java.util.List;
import java.util.Map;

public interface PerssionService {

    Permission insert(Permission permission);

    void delete(Long permissionId);

    Permission findById(Long permissionId);

    Permission updatePermission(Permission permission);

    List<PermissionVo> tree(Map param);
}
