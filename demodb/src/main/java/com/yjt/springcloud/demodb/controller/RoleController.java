package com.yjt.springcloud.demodb.controller;

import com.alibaba.fastjson.JSONObject;
import com.yjt.springcloud.demodb.entity.Role;
import com.yjt.springcloud.demodb.service.RoleService;
import com.yjt.springcloud.demodb.web.JsonTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色
 * ClassName: RoleController
 * Date: 2019-09-05 20:02
 * author Administrator
 * version V1.0
 */
@Slf4j
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /*
     * 角色新增
     * @author Administrator
     * @date 2019-09-05 20:05
     * @param role
     * @return org.springframework.web.client.RestTemplate
     */
    @PostMapping("insert")
    public JsonTemplate addRole(@RequestBody Role role){
       return JsonTemplate.success(roleService.add(role),"角色添加成功");
    }

    /*
     * 角色修改
     * @author Administrator
     * @date 2019-09-05 20:09
     * @param role
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("update")
    public JsonTemplate updateRole(@RequestBody Role role){
        return JsonTemplate.success(roleService.update(role),"角色修改成功");
    }
    /*
     * 角色查看
     * @author Administrator
     * @date 2019-09-05 20:12
     * @param roleId
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("get/{roleId}")
    public JsonTemplate getRole(@PathVariable Long roleId){
        return JsonTemplate.success(roleService.findById(roleId),"角色查询成功");
    }

    /*
     * 更新状态
     * @author Administrator
     * @date 2019-09-05 20:16
     * @param param
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("updateRoleState")
    public JsonTemplate updateRoleState(@RequestBody Map param){
        return JsonTemplate.success(roleService.updateStateById(param),"角色状态更新成功");
    }

    /*
     * 角色删除
     * @author Administrator
     * @date 2019-09-09 20:58
     * @param roleId
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("delete/{roleId}")
    public JsonTemplate deleteRole(@PathVariable Long roleId){
        roleService.deleteById(roleId);
        return JsonTemplate.success(null,"角色删除成功");
    }

    /*
     * 角色分配用户
     * @author Administrator
     * @date 2019-09-10 7:38
     * @param
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("assignUser")
    public JsonTemplate assignUser(@RequestBody JSONObject jsonObject){
        Long roleId = jsonObject.getLong("roleId");
        List userIds = jsonObject.getJSONArray("userIds").toJavaList(Long.class);
        roleService.assginUser(userIds,roleId);
        return JsonTemplate.success(null,"角色分配人员成功");
    }

    /*
     * 角色树
     * @author Administrator
     * @date 2019-09-11 20:24
     * @param param
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("tree")
    public JsonTemplate tree(@RequestBody Map param){
        return JsonTemplate.success(roleService.tree(param),"角色树查询成功");
    }




}
