package com.yjt.springcloud.demodb.service.mapper;

import com.yjt.springcloud.demodb.entity.GroupRole;
import com.yjt.springcloud.demodb.entity.UserRole;
import com.yjt.springcloud.demodb.entity.dto.GroupRoleVo;
import com.yjt.springcloud.demodb.entity.dto.UserRoleVo;
import com.yjt.springcloud.demodb.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

/**
 * TODO
 * ClassName: UserRoleMapper
 * Date: 2019-09-19 23:51
 * author Administrator
 * version V1.0
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleMapper extends EntityMapper<UserRoleVo, UserRole> {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "user.userName", target = "userName"),
            @Mapping(source = "user.org.orgName", target = "orgName"),
            @Mapping(source = "user.nickName", target = "nickName"),
            @Mapping(source = "user.sex", target = "sex"),
            @Mapping(source = "user.mobile", target = "mobile"),
            @Mapping(source = "role.id", target = "roleId"),
            @Mapping(source = "role.roleCode", target = "roleCode"),
            @Mapping(source = "role.roleName", target = "roleName")
    })
    @Override
    UserRoleVo toDto(UserRole entity);
}
