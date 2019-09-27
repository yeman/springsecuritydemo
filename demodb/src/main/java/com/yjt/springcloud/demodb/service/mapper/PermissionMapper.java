package com.yjt.springcloud.demodb.service.mapper;

import com.yjt.springcloud.demodb.entity.Permission;
import com.yjt.springcloud.demodb.entity.dto.PermissionVo;
import com.yjt.springcloud.demodb.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * TODO
 * ClassName: PermissionMapper
 * Date: 2019-09-19 21:05
 * author Administrator
 * version V1.0
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper extends EntityMapper<PermissionVo,Permission> {
}
