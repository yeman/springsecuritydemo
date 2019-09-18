package com.yjt.springcloud.demodb.entity.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 * ClassName: RoleTreeVo
 * Date: 2019-09-02 23:51
 * author Administrator
 * version V1.0
 */
@Data
public class RoleTreeVo implements Serializable {

    private Long id;

    private String roleCode;

    private String roleName;

    private String sortOrder;

    private List<RoleTreeVo> children = Lists.newArrayList();
}
