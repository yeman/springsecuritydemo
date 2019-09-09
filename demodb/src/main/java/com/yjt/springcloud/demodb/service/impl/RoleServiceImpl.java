package com.yjt.springcloud.demodb.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.yjt.springcloud.demodb.entity.Role;
import com.yjt.springcloud.demodb.entity.RoleDataPermission;
import com.yjt.springcloud.demodb.repository.OrgRepository;
import com.yjt.springcloud.demodb.repository.RoleDataPermissionRepository;
import com.yjt.springcloud.demodb.repository.RoleRepository;
import com.yjt.springcloud.demodb.service.RoleService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 角色
 * ClassName: RoleServiceImpl
 * Date: 2019-09-05 20:04
 * author Administrator
 * version V1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleDataPermissionRepository roleDataPermissionRepository;
    @Autowired
    private OrgRepository orgRepository;

    @Override
    public Role add(Role role) {
        role.setId(IdWorker.getId());
        roleRepository.save(role);
        if(!role.getRoleDataPermissions().isEmpty()){
            role.getRoleDataPermissions().stream().forEach(roleDataPermission -> {
                roleDataPermission.setId(IdWorker.getId());
                roleDataPermission.setOrg(roleDataPermission.getOrg());
                roleDataPermission.setRole(role);
                roleDataPermissionRepository.save(roleDataPermission);
            });
        }
        return role;
    }

    @Override
    public Role update(Role role) {
        Role r =  roleRepository.update(role);
        if(!r.getRoleDataPermissions().isEmpty()){
            roleDataPermissionRepository.deleteByRole(role.getId());
            role.getRoleDataPermissions().stream().forEach(roleDataPermission -> {
                roleDataPermission.setId(IdWorker.getId());
                roleDataPermission.setOrg(roleDataPermission.getOrg());
                roleDataPermission.setRole(role);
                roleDataPermissionRepository.save(roleDataPermission);
            });
        }
        return r;
    }

    @Override
    public Role findById(Long roleId) {
        return roleRepository.findById(roleId).get();
    }

    @Override
    public Role updateStateById(Map param) {
        Long roleId = MapUtils.getLong(param,"id");
        Role role = roleRepository.findById(roleId).get();
        role.setState(MapUtils.getString(param,"state"));
        return roleRepository.update(role);
    }

    @Override
    public void deleteById(Long roleId) {
        roleRepository.deleteById(roleId);
    }
}
