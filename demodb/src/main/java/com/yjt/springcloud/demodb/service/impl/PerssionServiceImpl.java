package com.yjt.springcloud.demodb.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.collect.Lists;
import com.yjt.springcloud.demodb.entity.Permission;
import com.yjt.springcloud.demodb.entity.RolePermission;
import com.yjt.springcloud.demodb.entity.dto.PermissionVo;
import com.yjt.springcloud.demodb.repository.PermissionRepository;
import com.yjt.springcloud.demodb.repository.RolePermissionRepository;
import com.yjt.springcloud.demodb.service.PerssionService;
import com.yjt.springcloud.demodb.service.mapper.PermissionMapper;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PerssionServiceImpl implements PerssionService {

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;
    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public Permission insert(Permission permission) {
        permission.setId(IdWorker.getId());
        return permissionRepository.save(permission);
    }

    @Override
    public void delete(Long permissionId) {
        Permission permission = permissionRepository.findById(permissionId).get();
        List<Permission> permissionList = Lists.newArrayList();
        permissionChildrenList(permission,permissionList);
        permissionList.forEach(p->{
            permissionRepository.deleteById(p.getId());
            List<RolePermission> rolePermissionList = rolePermissionRepository.findByPermission(p);
            rolePermissionRepository.deleteAll(rolePermissionList);
        });
    }

    protected List<Permission> permissionChildrenList(Permission permission,List<Permission> children){
        children.add(permission);
        List<Permission> childrens = permissionRepository.findByParentIdOrderBySortOrder(permission.getId());
        if(!childrens.isEmpty()){
            children.addAll(childrens);
            children.stream().forEach(e->{
                permissionChildrenList(e,permissionRepository.findByParentIdOrderBySortOrder(e.getId()));
            });
        }
        return children;
    }

    @Override
    public Permission findById(Long permissionId) {
        return permissionRepository.findById(permissionId).get();
    }

    @Override
    public Permission updatePermission(Permission permission) {
        return permissionRepository.update(permission);
    }

    @Override
    public List<PermissionVo> tree(Map param) {
        List result = Lists.newArrayList();
        if (MapUtils.getLong(param, "id") != null) {
            Optional<Permission> current = permissionRepository.findById(MapUtils.getLong(param, "id"));
            if(current.isPresent()){
                Permission permission=  bulidLeaf(current.get(), Lists.newArrayList());
                result.add(permissionMapper.toDto(permission));
            }
        } else {
            List<Permission> permissionList = permissionRepository.findByParentIdIsNullOrderBySortOrder();
            for(Permission p:permissionList){
                Permission permission=  bulidLeaf(p, Lists.newArrayList());
                result.add(permissionMapper.toDto(permission));
            }

        }
        return result;
    }

    protected Permission bulidLeaf(Permission permission, List<Permission> children) {
        List<Permission> childrens = permissionRepository.findByParentIdOrderBySortOrder(permission.getId());
        if (!childrens.isEmpty()) {
            permission.setChildren(childrens);
            childrens.stream().forEach(e -> {
                bulidLeaf(e, permissionRepository.findByParentIdOrderBySortOrder(e.getId()));
            });
        }
        return permission;
    }
}
