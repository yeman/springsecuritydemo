package com.yjt.springcloud.demodb.service.mapper;

import com.yjt.springcloud.demodb.entity.User;
import com.yjt.springcloud.demodb.entity.dto.UserListVo;
import com.yjt.springcloud.demodb.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * TODO
 * ClassName: UserListMapper
 * Date: 2019-09-10 21:04
 * author Administrator
 * version V1.0
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserListMapper extends EntityMapper<UserListVo, User> {
}
