package com.yjt.springcloud.demodb.security;

import com.google.common.collect.Lists;
import com.yjt.springcloud.demodb.entity.*;
import com.yjt.springcloud.demodb.repository.UserRoleRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TODO
 * ClassName: JwtPermissionService
 * Date: 2019-10-21 22:23
 * author Administrator
 * version V1.0
 */
@Service
public class JwtPermissionService {
    private UserRoleRepository userRoleRepository;

    public Collection<GrantedAuthority> mapToGrantedAuthorities(User userEntity) {
        Set<UserRole> userRoles = userEntity.getRoles();
        Set<UserGroup> userGroups = userEntity.getGroups();
        List<GrantedAuthority> authorityList = Lists.newArrayList();
        List<Permission> allPermissionList = Lists.newArrayList();
        List<Permission> permissionList = userRoles.stream().map(UserRole::getRole).flatMap(role -> role.getPermissions().stream().map(RolePermission::getPermission)).collect(Collectors.toList());
        List<Permission> groupPermissionList = userGroups.stream().map(UserGroup::getGroup).flatMap(group -> group.getGroupRoles().stream().map(GroupRole::getRole)).flatMap(role -> role.getPermissions().stream().map(RolePermission::getPermission)).collect(Collectors.toList());
        allPermissionList.addAll(permissionList);
        allPermissionList.addAll(groupPermissionList);
        allPermissionList.stream().map(permission -> new SimpleGrantedAuthority(permission.getId().toString())).collect(Collectors.toList());
        return authorityList;
    }
}
