package com.yjt.springcloud.demodb.service.mapper;

import com.yjt.springcloud.demodb.entity.Role;
import com.yjt.springcloud.demodb.entity.dto.RoleTreeVo;
import com.yjt.springcloud.demodb.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * TODO
 * ClassName: RoleMapper
 * Date: 2019-09-03 0:12
 * author Administrator
 * version V1.0
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends EntityMapper<RoleTreeVo, Role> {
}
