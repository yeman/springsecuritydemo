package com.yjt.springcloud.demodb.service.impl;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.collect.Lists;
import com.yjt.springcloud.demodb.entity.*;
import com.yjt.springcloud.demodb.entity.dto.GroupTreeVo;
import com.yjt.springcloud.demodb.repository.*;
import com.yjt.springcloud.demodb.service.GroupService;
import com.yjt.springcloud.demodb.service.condition.GroupRoleQueryCriteria;
import com.yjt.springcloud.demodb.service.condition.GroupUserQueryCriteria;
import com.yjt.springcloud.demodb.service.mapper.GroupMapper;
import com.yjt.springcloud.demodb.service.mapper.GroupRoleMapper;
import com.yjt.springcloud.demodb.service.mapper.UserGroupMapper;
import com.yjt.springcloud.demodb.util.QueryHelp;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 组
 * ClassName: GroupServiceImpl
 * Date: 2019-09-12 19:17
 * author Administrator
 * version V1.0
 */
@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupRoleRepository groupRoleRepository;
    @Autowired
    private UserGroupRepository userGroupRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private GroupRoleMapper groupRoleMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;

    @Override
    public void delete(@NotNull Long groupId) {
        Group group = groupRepository.findById(groupId).get();
        List<Group> groupList = Lists.newArrayList();
        groupChildrenList(group,groupList);
        groupList.forEach(e->{
            groupRepository.deleteById(e.getGroupId());
            List<GroupRole> groupRoleList = groupRoleRepository.findByGroup(e);
            groupRoleRepository.deleteAll(groupRoleList);
            List<UserGroup> userGroups = userGroupRepository.findByGroup(e);
            userGroupRepository.deleteAll(userGroups);
        });
    }

    protected List<Group> groupChildrenList(Group group,List<Group> children){
        children.add(group);
        List<Group> childrens = groupRepository.findByParentIdOrderBySortOrder(group.getGroupId());
        if(!childrens.isEmpty()){
            children.addAll(childrens);
            children.stream().forEach(e->{
                groupChildrenList(e,groupRepository.findByParentIdOrderBySortOrder(e.getGroupId()));
            });
        }
        return children;
    }

    @Override
    public Group insert(@NotNull Group group) {
        group.setGroupId(IdWorker.getId());
        return groupRepository.save(group);
    }

    @Override
    public Group updateGroup(Group group) {
        Group g =  groupRepository.update(group);
        if(!g.getGroupRoles().isEmpty()){
            groupRoleRepository.deleteByGroup(group.getGroupId());
            group.getGroupRoles().stream().forEach(groupRole -> {
                groupRole.setId(IdWorker.getId());
                groupRole.setRole(groupRole.getRole());
                groupRole.setGroup(group);
                groupRoleRepository.save(groupRole);
            });
        }else{
            groupRoleRepository.deleteByGroup(group.getGroupId());
        }
        return group;
    }

    @Override
    public Group findById(@NotNull Long groupId) {
        return groupRepository.findById(groupId).get();
    }

    @Override
    public GroupTreeVo tree(Map param) {
        if (MapUtils.getLong(param, "id") != null) {
            Optional<Group> current = groupRepository.findById(MapUtils.getLong(param, "id"));
            if(current.isPresent()){
                Group group=  bulidLeaf(current.get(), Lists.newArrayList());
                return groupMapper.toDto(group);
            }
        } else {
            Optional<Group> current = groupRepository.findByParentIdIsNullOrderBySortOrder();
            if(current.isPresent()){
                Group group=  bulidLeaf(current.get(), Lists.newArrayList());
                return groupMapper.toDto(group);
            }
        }
        return null;
    }

    @Override
    public Page groupRole(GroupRoleQueryCriteria criteria, Pageable pageable) {
        Page<GroupRole> page = groupRoleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return page.map(groupRoleMapper::toDto);
    }

    @Override
    public void insertRole(JSONObject jsonObject) {
        Long groupId = jsonObject.getLong("groupId");
        Assert.notNull(groupId,"组不为空");
        Assert.notEmpty(jsonObject.getJSONArray("roleIds"),"角色不为空");
        jsonObject.getJSONArray("roleIds").stream().forEach(roleId->{
            GroupRole groupRole = new GroupRole();
            groupRole.setGroup(groupRepository.getOne(groupId));
            groupRole.setRole(roleRepository.getOne((Long) roleId));
            List list = groupRoleRepository.findAll(Example.of(groupRole));
            if(list.isEmpty()){
                groupRole.setId(IdWorker.getId());
                groupRoleRepository.save(groupRole);
            }
        });
    }

    @Override
    public void delRole(JSONArray jsonArray) {
        Assert.notEmpty(jsonArray,"组角色不为空");
        jsonArray.stream().forEach(id->{
            groupRoleRepository.deleteById((Long) id);
        });
    }

    @Override
    public Page groupUser(GroupUserQueryCriteria criteria, Pageable pageable) {
        Page<UserGroup> page = userGroupRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return page.map(userGroupMapper::toDto);
    }

    @Override
    public void insertUser(JSONObject jsonObject) {
        Long groupId = jsonObject.getLong("groupId");
        Assert.notNull(groupId,"组不为空");
        Assert.notEmpty(jsonObject.getJSONArray("userIds"),"用户不为空");
        jsonObject.getJSONArray("userIds").stream().forEach(userId->{
            UserGroup userGroup = new UserGroup();
            userGroup.setGroup(groupRepository.getOne(groupId));
            userGroup.setUser(userRepository.getOne((Long) userId));
            List list = userGroupRepository.findAll(Example.of(userGroup));
            if(list.isEmpty()){
                userGroup.setId(IdWorker.getId());
                userGroupRepository.save(userGroup);
            }
        });
    }

    @Override
    public void delUser(JSONArray jsonArray) {
        Assert.notEmpty(jsonArray,"组人员不为空");
        jsonArray.stream().forEach(id->{
            userGroupRepository.deleteById((Long) id);
        });
    }

    protected Group bulidLeaf(Group group, List<Group> children) {
        List<Group> childrens = groupRepository.findByParentIdOrderBySortOrder(group.getGroupId());
        if (!childrens.isEmpty()) {
            group.setChildren(childrens);
            childrens.stream().forEach(e -> {
                bulidLeaf(e, groupRepository.findByParentIdOrderBySortOrder(e.getGroupId()));
            });
        }
        return group;
    }
}
