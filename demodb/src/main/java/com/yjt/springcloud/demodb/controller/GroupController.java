package com.yjt.springcloud.demodb.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yjt.springcloud.demodb.entity.Group;
import com.yjt.springcloud.demodb.service.GroupService;
import com.yjt.springcloud.demodb.service.condition.GroupRoleQueryCriteria;
import com.yjt.springcloud.demodb.service.condition.GroupUserQueryCriteria;
import com.yjt.springcloud.demodb.web.JsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 组
 * ClassName: GroupController
 * Date: 2019-09-12 19:18
 * author Administrator
 * version V1.0
 */
@RestController
@RequestMapping("system/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    /*
     * 组
     * @author Administrator
     * @date 2019-09-12 19:25
     * @param group
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("insert")
    public JsonTemplate insert(@RequestBody Group group){
        return JsonTemplate.success(groupService.insert(group),"添加成功");
    }

    /*
     * 组删除
     * @author Administrator
     * @date 2019-08-31 23:26
     * @param groupId
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("delete/{groupId}")
    public JsonTemplate delOrg(@PathVariable Long groupId){
        groupService.delete(groupId);
        return JsonTemplate.success(null,"删除成功");
    }

    /*
     * 根据id查询
     * @author Administrator
     * @date 2019-09-01 21:07
     * @param groupId
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @GetMapping("get/{groupId}")
    public JsonTemplate get(@PathVariable Long groupId){
        return JsonTemplate.success(groupService.findById(groupId),"查询成功");
    }

    /*
     * 机构修改
     * @author Administrator
     * @date 2019-09-01 20:03
     * @param group
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("update")
    public JsonTemplate updateGroup(@RequestBody Group group){
        return JsonTemplate.success( groupService.updateGroup(group),"修改成功");
    }

    /*
     * 组树
     * @author Administrator
     * @date 2019-09-01 20:07
     * @param
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("tree")
    public JsonTemplate tree(@RequestBody Map param){
        return JsonTemplate.success(groupService.tree(param),"查询成功");
    }

    /*
     * 组角色分页数据
     * @author Administrator
     * @date 2019-09-16 21:13
     * @param
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("groupRolePage")
    public JsonTemplate groupRole(GroupRoleQueryCriteria criteria, @PageableDefault(value = 10, sort = {"createTime"}, direction = Sort.Direction.ASC) Pageable pageable){
        return JsonTemplate.success(groupService.groupRole(criteria,pageable),"查询成功");
    }
    /*
     * 分配角色
     * @author Administrator
     * @date 2019-09-16 20:49
     * @param jsonObject
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("assginRole")
    public JsonTemplate assginRole(@RequestBody JSONObject jsonObject){
        groupService.insertRole(jsonObject);
        return JsonTemplate.success(null,"组角色分配成功");
    }

    /*
     * 删除角色
     * @author Administrator
     * @date 2019-09-16 20:49
     * @param jsonArray
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("delRole")
    public JsonTemplate delRole(@RequestBody JSONArray jsonArray){
        groupService.delRole(jsonArray);
        return JsonTemplate.success(null,"组角色删除成功");
    }

    /*
     * 组用户分页查询
     * @author Administrator
     * @date 2019-09-19 1:27
     * @param criteria
     * @param pageable
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("groupUserPage")
    public JsonTemplate groupUserPage(GroupUserQueryCriteria criteria, @PageableDefault(value = 10, sort = {"createTime"}, direction = Sort.Direction.ASC) Pageable pageable){
        return JsonTemplate.success(groupService.groupUser(criteria,pageable),"查询成功");
    }

    /*
     * 分配人员
     * @author Administrator
     * @date 2019-09-16 20:49
     * @param jsonObject
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("assginUser")
    public JsonTemplate assginUser(@RequestBody JSONObject jsonObject){
        groupService.insertUser(jsonObject);
        return JsonTemplate.success(null,"组人员分配成功");
    }

    @PostMapping("delUser")
    public JsonTemplate delUser(@RequestBody JSONArray jsonArray){
        groupService.delUser(jsonArray);
        return JsonTemplate.success(null,"组人员删除成功");
    }

}
