package com.yjt.springcloud.demodb.mapper;

import java.util.List;

/**
 * DTO 和 实体转换
 * ClassName: EntityMapper
 * Date: 2019-09-03 0:12
 * author Administrator
 * version V1.0
 */
public interface EntityMapper<D,E> {

    E toEntity(D dto);

    D toDto(E entity);


    List<E> toEntity(List<D> dtoList);


    List <D> toDto(List<E> entityList);

}
