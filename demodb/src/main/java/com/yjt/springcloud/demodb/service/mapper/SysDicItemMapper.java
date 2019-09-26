package com.yjt.springcloud.demodb.service.mapper;

import com.yjt.springcloud.demodb.entity.SysDic;
import com.yjt.springcloud.demodb.entity.SysDicItem;
import com.yjt.springcloud.demodb.entity.dto.SysDicItemVo;
import com.yjt.springcloud.demodb.entity.dto.SysDicVo;
import com.yjt.springcloud.demodb.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * TODO
 * ClassName: SysDicItemMapper
 * Date: 2019-09-24 0:06
 * author Administrator
 * version V1.0
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysDicItemMapper extends EntityMapper<SysDicItemVo, SysDicItem> {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "sysDic.dicType", target = "dicType"),
            @Mapping(source = "labelCode", target = "labelCode"),
            @Mapping(source = "labelName", target = "labelName"),
            @Mapping(source = "enable", target = "enable"),
            @Mapping(source = "sortOrder", target = "sortOrder"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "createTime", target = "createTime",dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    @Override
    SysDicItemVo toDto(SysDicItem entity);
}
