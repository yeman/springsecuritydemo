package com.yjt.springcloud.demodb.entity.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 组树
 * ClassName: GroupTreeVo
 * Date: 2019-09-02 23:51
 * author Administrator
 * version V1.0
 */
@Data
public class GroupTreeVo implements Serializable {

    private Long groupId;

    private String groupName;

    private String sortOrder;

    private List<GroupTreeVo> children = Lists.newArrayList();
}
