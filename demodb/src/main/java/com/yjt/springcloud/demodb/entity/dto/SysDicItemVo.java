package com.yjt.springcloud.demodb.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * TODO
 * ClassName: SysDicItemVo
 * Date: 2019-09-25 23:22
 * author Administrator
 * version V1.0
 */
@Data
public class SysDicItemVo {
    private Long id;

    private String dicType;

    private String labelCode;

    private String labelName;

    private String enable;

    private String sortOrder;

    private String description;

    private String createTime;
}
