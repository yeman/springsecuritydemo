package com.yjt.springcloud.demodb.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yjt.springcloud.demodb.entity.Group;
import com.yjt.springcloud.demodb.entity.dto.GroupTreeVo;
import com.yjt.springcloud.demodb.service.dto.GroupRoleQueryCriteria;
import com.yjt.springcloud.demodb.service.dto.GroupUserQueryCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 组服务
 * ClassName: GroupService
 * Date: 2019-09-12 19:17
 * author Administrator
 * version V1.0
 */
public interface GroupService {

    void delete(@NotNull Long groupId);

    Group insert(@NotNull Group group);

    Group updateGroup(Group group);

    Group findById(@NotNull Long groupId);

    GroupTreeVo tree(Map param);

    Page groupRole(GroupRoleQueryCriteria criteria, Pageable pageable);

    void assginRole(JSONObject jsonObject);

    void delRole(JSONArray jsonArray);

    Page groupUser(GroupUserQueryCriteria criteria, Pageable pageable);

    void assginUser(JSONObject jsonObject);

    void delUser(JSONArray jsonArray);
}
