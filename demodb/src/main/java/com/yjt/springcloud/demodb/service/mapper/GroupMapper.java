package com.yjt.springcloud.demodb.service.mapper;

import com.yjt.springcloud.demodb.entity.Group;
import com.yjt.springcloud.demodb.entity.dto.GroupTreeVo;
import com.yjt.springcloud.demodb.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 组
 * ClassName: GroupMapper
 * Date: 2019-09-12 20:01
 * author Administrator
 * version V1.0
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupMapper extends EntityMapper<GroupTreeVo, Group> {
}
