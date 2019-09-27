package com.yjt.springcloud.demodb.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yjt.springcloud.demodb.entity.Permission;
import com.yjt.springcloud.demodb.entity.Role;
import com.yjt.springcloud.demodb.entity.dto.PermissionVo;
import com.yjt.springcloud.demodb.entity.dto.RoleTreeVo;
import com.yjt.springcloud.demodb.service.condition.RolePermissionQueryCriteria;
import com.yjt.springcloud.demodb.service.condition.UserRoleQueryCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    void insertUser(List<Long> userIds, Long roleId);

    RoleTreeVo tree(Map param);

    List<PermissionVo> queryPermission(Long roleId);

    void delUser(JSONArray jsonArray);

    Page roleUser(UserRoleQueryCriteria criteria, Pageable pageable);

    Page roleUserWaitAssginPage(UserRoleQueryCriteria criteria, Pageable pageable);

    void insertPermission(Long roleId, List<Long> permissionIds);
}
