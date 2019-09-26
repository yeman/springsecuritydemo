package com.yjt.springcloud.demodb.service.impl;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.collect.Lists;
import com.yjt.springcloud.demodb.entity.*;
import com.yjt.springcloud.demodb.entity.dto.OrgTreeVo;
import com.yjt.springcloud.demodb.entity.dto.PermissionVo;
import com.yjt.springcloud.demodb.entity.dto.RoleTreeVo;
import com.yjt.springcloud.demodb.enums.RoleDataScopeEnum;
import com.yjt.springcloud.demodb.repository.*;
import com.yjt.springcloud.demodb.service.RoleService;
import com.yjt.springcloud.demodb.service.condition.UserRoleQueryCriteria;
import com.yjt.springcloud.demodb.service.mapper.PermissionMapper;
import com.yjt.springcloud.demodb.service.mapper.RoleMapper;
import com.yjt.springcloud.demodb.service.mapper.UserListMapper;
import com.yjt.springcloud.demodb.service.mapper.UserRoleMapper;
import com.yjt.springcloud.demodb.util.QueryHelp;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private RolePermissionRepository rolePermissionRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserListMapper userListMapper;
    @Autowired
    private PermissionMapper permissionMapper;

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
            List<RolePermission> rolePermissionList = rolePermissionRepository.queryByRole(e);
            rolePermissionRepository.deleteAll(rolePermissionList);
        });

    }

    @Override
    public void insertUser(List<Long> userIds, Long roleId) {
        List<UserRole> userRoleList = Lists.newArrayList();
        Role role = roleRepository.findById(roleId).get();
        userRoleRepository.deleteByRole(role);
        for (Long userId: userIds) {
            UserRole userRole = new UserRole();
            userRole.setRole(role);
            userRole.setUser(userRepository.getOne(userId));
            userRole.setId(IdWorker.getId());
            userRoleList.add(userRole);
        }
        if(!userRoleList.isEmpty()){
            userRoleRepository.saveAll(userRoleList);
        }

    }
    @Override
    public void delUser(JSONArray jsonArray) {
        Assert.notEmpty(jsonArray,"角色用户不为空");
        jsonArray.stream().forEach(e->{
            userRoleRepository.deleteById((Long) e);
        });
    }


    @Override
    public Page roleUser(UserRoleQueryCriteria criteria, Pageable pageable) {
        Page<UserRole> page = userRoleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return page.map(userRoleMapper::toDto);
    }

    @Override
    public Page roleUserWaitAssginPage(UserRoleQueryCriteria condition, Pageable pageable) {
        Page<User> page = userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = Lists.newArrayList();
                if(StringUtils.isNotBlank(condition.getUserName())){
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("userName"),"%"+condition.getUserName()+"%")));
                }
                if(StringUtils.isNotBlank(condition.getNickName())){
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("nickName"),"%"+condition.getNickName()+"%")));
                }
                if(StringUtils.isNotBlank(condition.getMobile())){
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("mobile"),"%"+condition.getMobile()+"%")));
                }
                if(StringUtils.isNotBlank(condition.getEmail())){
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("email"),"%"+condition.getEmail()+"%")));
                }
                Subquery subquery =  query.subquery(String.class);
                Root<UserRole> userRole = subquery.from(UserRole.class);
                subquery.select(userRole.get("user").get("id")).where(criteriaBuilder.equal(userRole.get("role").get("id"),condition.getRoleId()));
                predicates.add(criteriaBuilder.not(criteriaBuilder.in(root.get("id")).value(subquery)));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        },pageable);
        return page.map(userListMapper::toDto);
    }

    @Override
    public void insertPermission(Long roleId, List<Long> permissionIds) {
        List<RolePermission> rolePermissionList = Lists.newArrayList();
        Role role = roleRepository.findById(roleId).get();
        rolePermissionRepository.deleteByRole(role);
        for (Long permissionId: permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRole(role);
            rolePermission.setPermission(permissionRepository.getOne(permissionId));
            rolePermission.setId(IdWorker.getId());
            rolePermissionList.add(rolePermission);


        }
        if(!rolePermissionList.isEmpty()){
            rolePermissionRepository.saveAll(rolePermissionList);
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
            Optional<Role> current = roleRepository.findByParentIdIsNullOrderBySortOrder();
            if(current.isPresent()){
                Role role=  bulidLeaf(current.get(), Lists.newArrayList());
                return roleMapper.toDto(role);
            }
        }
        return null;
    }

    @Override
    public List<PermissionVo> queryPermission(Long roleId) {
        List<RolePermission> rolePermissionList = rolePermissionRepository.queryByRole(roleRepository.getOne(roleId));
        return permissionMapper.toDto(rolePermissionList.stream().map(RolePermission::getPermission).collect(Collectors.toList()));
    }



    protected Role bulidLeaf(Role role, List<Role> children) {
        List<Role> childrens = roleRepository.findByParentIdOrderBySortOrder(role.getId());
        if (!childrens.isEmpty()) {
            role.setChildren(childrens);
            childrens.stream().forEach(e -> {
                bulidLeaf(e, roleRepository.findByParentIdOrderBySortOrder(e.getId()));
            });
        }
        return role;
    }

    protected List<Role> roleChildrenList(Role role,List<Role> children){
        children.add(role);
        List<Role> childrens = roleRepository.findByParentIdOrderBySortOrder(role.getId());
        if(!children.isEmpty()){
            children.addAll(childrens);
            children.stream().forEach(e->{
                roleChildrenList(e,roleRepository.findByParentIdOrderBySortOrder(e.getId()));
            });
        }
        return children;
    }
}
