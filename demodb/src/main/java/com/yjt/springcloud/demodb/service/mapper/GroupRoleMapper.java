package com.yjt.springcloud.demodb.service.mapper;

import com.yjt.springcloud.demodb.entity.GroupRole;
import com.yjt.springcloud.demodb.entity.dto.GroupRoleVo;
import com.yjt.springcloud.demodb.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

/**
 * TODO
 * ClassName: GroupRoleMapper
 * Date: 2019-09-18 21:34
 * author Administrator
 * version V1.0
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupRoleMapper extends EntityMapper<GroupRoleVo, GroupRole>{
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "group.groupName", target = "groupName"),
            @Mapping(source = "role.id", target = "roleId"),
            @Mapping(source = "role.roleCode", target = "roleCode"),
            @Mapping(source = "role.roleName", target = "roleName")
    })
    @Override
    GroupRoleVo toDto(GroupRole entity);
}
