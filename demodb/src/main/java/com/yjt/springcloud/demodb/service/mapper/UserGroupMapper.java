package com.yjt.springcloud.demodb.service.mapper;

import com.yjt.springcloud.demodb.entity.UserGroup;
import com.yjt.springcloud.demodb.entity.dto.UserGroupVo;
import com.yjt.springcloud.demodb.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

/**
 * TODO
 * ClassName: UserGroupMapper
 * Date: 2019-09-19 1:34
 * author Administrator
 * version V1.0
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserGroupMapper extends EntityMapper<UserGroupVo, UserGroup> {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "group.groupName", target = "groupName"),
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "user.org.orgName", target = "orgName"),
            @Mapping(source = "user.email", target = "email"),
            @Mapping(source = "user.sex", target = "sex"),
            @Mapping(source = "user.mobile", target = "mobile"),
    })
    @Override
    UserGroupVo toDto(UserGroup entity);
}
