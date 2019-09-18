package com.yjt.springcloud.demodb.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.collect.Lists;
import com.yjt.springcloud.demodb.entity.Org;
import com.yjt.springcloud.demodb.entity.Role;
import com.yjt.springcloud.demodb.entity.RoleDataPermission;
import com.yjt.springcloud.demodb.entity.UserRole;
import com.yjt.springcloud.demodb.entity.dto.OrgTreeVo;
import com.yjt.springcloud.demodb.entity.dto.RoleTreeVo;
import com.yjt.springcloud.demodb.enums.RoleDataScopeEnum;
import com.yjt.springcloud.demodb.repository.*;
import com.yjt.springcloud.demodb.service.RoleService;
import com.yjt.springcloud.demodb.service.mapper.RoleMapper;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleMapper roleMapper;

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
        if(!r.getRoleDataPermissions().isEmpty() && RoleDataScopeEnum.CUSTOMER.getType().equals(role.getDataScope())){
            roleDataPermissionRepository.deleteByRole(role.getId());
            role.getRoleDataPermissions().stream().forEach(roleDataPermission -> {
                roleDataPermission.setId(IdWorker.getId());
                roleDataPermission.setOrg(roleDataPermission.getOrg());
                roleDataPermission.setRole(role);
                roleDataPermissionRepository.save(roleDataPermission);
            });
        }else{
            roleDataPermissionRepository.deleteByRole(role.getId());
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
        List<Role> roles = Lists.newArrayList();
        roleChildrenList(role,roles);
        roles.stream().forEach(r -> {
            r.setState(MapUtils.getString(param,"state"));
            roleRepository.update(r);
        });

        return role;
    }

    @Override
    public void deleteById(Long roleId) {
        Role role = roleRepository.findById(roleId).get();
        List<Role> roles = Lists.newArrayList();
        roleChildrenList(role,roles);
        roles.forEach(e->{
            roleRepository.deleteById(e.getId());
            List<UserRole> userRoleList = userRoleRepository.findByRole(e);
            userRoleRepository.deleteAll(userRoleList);
        });

    }

    @Override
    public void assginUser(List<Long> userIds, Long roleId) {
        List<UserRole> userRoleList = Lists.newArrayList();
        Role role = roleRepository.findById(roleId).get();
        for (Long userId: userIds) {
            UserRole userRole = new UserRole();
            userRole.setId(IdWorker.getId());
            userRole.setRole(role);
            userRole.setUser(userRepository.getOne(userId));
            userRoleList.add(userRole);
        }
        if(!userRoleList.isEmpty()){
            userRoleRepository.saveAll(userRoleList);
        }

    }

    @Override
    public RoleTreeVo tree(Map param) {
        if (MapUtils.getLong(param, "id") != null) {
            Optional<Role> current = roleRepository.findById(MapUtils.getLong(param, "id"));
            if(current.isPresent()){
                Role role=  bulidLeaf(current.get(), Lists.newArrayList());
                return roleMapper.toDto(role);
            }
        } else {
            Optional<Role> current = roleRepository.findByParentIdIsNull();
            if(current.isPresent()){
                Role role=  bulidLeaf(current.get(), Lists.newArrayList());
                return roleMapper.toDto(role);
            }
        }
        return null;
    }
    protected Role bulidLeaf(Role role, List<Role> children) {
        List<Role> childrens = roleRepository.findByParentId(role.getId());
        if (!childrens.isEmpty()) {
            role.setChildren(childrens);
            childrens.stream().forEach(e -> {
                bulidLeaf(e, roleRepository.findByParentId(e.getId()));
            });
        }
        return role;
    }

    protected List<Role> roleChildrenList(Role role,List<Role> children){
        children.add(role);
        List<Role> childrens = roleRepository.findByParentId(role.getId());
        if(!children.isEmpty()){
            children.addAll(childrens);
            children.stream().forEach(e->{
                roleChildrenList(e,roleRepository.findByParentId(e.getId()));
            });
        }
        return children;
    }
}
