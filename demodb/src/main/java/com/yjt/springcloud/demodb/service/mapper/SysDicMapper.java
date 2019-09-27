package com.yjt.springcloud.demodb.service.mapper;

import com.yjt.springcloud.demodb.entity.SysDic;
import com.yjt.springcloud.demodb.entity.dto.SysDicVo;
import com.yjt.springcloud.demodb.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

/**
 * TODO
 * ClassName: SysDicMapper
 * Date: 2019-09-24 0:06
 * author Administrator
 * version V1.0
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysDicMapper extends EntityMapper<SysDicVo, SysDic> {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "dicType", target = "dicType"),
            @Mapping(source = "dicTypeName", target = "dicTypeName"),
            @Mapping(source = "enable", target = "enable"),
            @Mapping(source = "sortOrder", target = "sortOrder"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "createTime", target = "createTime",dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    SysDicVo toDto(SysDic entity);
}
