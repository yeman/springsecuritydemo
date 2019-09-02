package com.yjt.springcloud.demodb.entity.dto;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 * ClassName: OrgTree
 * Date: 2019-09-02 23:51
 * author Administrator
 * version V1.0
 */
@Data
public class OrgTreeVo implements Serializable {

    private Long id;

    private String orgName;

    private List<OrgTreeVo> children = Lists.newArrayList();
}
