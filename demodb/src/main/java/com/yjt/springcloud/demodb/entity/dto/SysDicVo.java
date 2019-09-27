package com.yjt.springcloud.demodb.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 * ClassName: SysDicVo
 * Date: 2019-09-24 0:05
 * author Administrator
 * version V1.0
 */
@Data
public class SysDicVo {
    private Long id;
    private String dicType;
    private String dicTypeName;
    private String enable;
    private String sortOrder;
    private String description;
    private String createTime;
}
