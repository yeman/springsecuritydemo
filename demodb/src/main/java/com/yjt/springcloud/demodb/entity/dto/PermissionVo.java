package com.yjt.springcloud.demodb.entity.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * TODO
 * ClassName: PermissionVo
 * Date: 2019-09-19 21:09
 * author Administrator
 * version V1.0
 */
@Data
public class PermissionVo {

    private Long id;

    private String permissionType;

    private String permissionCode;

    private String permissionExpression;

    private String permissionName;

    private String sortOder;

    private String url;

    private String enable;

    private String icon;

    private List<PermissionVo> children = Lists.newArrayList();

}
