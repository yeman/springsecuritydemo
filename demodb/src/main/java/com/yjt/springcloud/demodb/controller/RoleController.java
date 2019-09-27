package com.yjt.springcloud.demodb.controller;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yjt.springcloud.demodb.entity.Role;
import com.yjt.springcloud.demodb.service.RoleService;
import com.yjt.springcloud.demodb.service.condition.GroupRoleQueryCriteria;
import com.yjt.springcloud.demodb.service.condition.RolePermissionQueryCriteria;
import com.yjt.springcloud.demodb.service.condition.UserRoleQueryCriteria;
import com.yjt.springcloud.demodb.web.JsonTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("system/role")
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
    @GetMapping("get/{roleId}")
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
     * 角色用户分页查询
     * @author Administrator
     * @date 2019-09-19 23:37
     * @param criteria
     * @param pageable
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("roleUserPage")
    public JsonTemplate roleUserPage(UserRoleQueryCriteria criteria, @PageableDefault(value = 10, sort = {"createTime"}, direction = Sort.Direction.ASC) Pageable pageable){
            return JsonTemplate.success(roleService.roleUser(criteria,pageable),"角色人员分页查询成功");
    }

    /*
     * 角色人员待分配分页数据查询
     * @author Administrator
     * @date 2019-09-20 0:04
     * @param criteria
     * @param pageable
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("roleUserWaitAssginPage")
    public JsonTemplate roleUserWaitAssginPage(UserRoleQueryCriteria criteria, @PageableDefault(value = 10, sort = {"createTime"}, direction = Sort.Direction.ASC) Pageable pageable){
        return JsonTemplate.success(roleService.roleUserWaitAssginPage(criteria,pageable),"角色人员待分配分页查询成功");
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
        Assert.notNull(roleId,"角色不为空");
        List userIds = jsonObject.getJSONArray("userIds").toJavaList(Long.class);
        roleService.insertUser(userIds,roleId);
        return JsonTemplate.success(null,"角色分配人员成功");
    }

    /*
     * 角色删除用户
     * @author Administrator
     * @date 2019-09-19 23:30
     * @param jsonArray
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("delUser")
    public JsonTemplate delUser(@RequestBody JSONArray jsonArray){
        roleService.delUser(jsonArray);
        return JsonTemplate.success(null,"角色删除人员成功");
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

    /*
     * 根据角色查询已分配权限
     * @author Administrator
     * @date 2019-09-21 16:41
     * @param roleId
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @GetMapping("/queryPermission/{roleId}")
    public JsonTemplate queryPermission(@PathVariable Long roleId){
        return JsonTemplate.success(roleService.queryPermission(roleId),"角色权限树查询成功");
    }
    @PostMapping("/assginPermission")
    public JsonTemplate assginPermission(@RequestBody JSONObject jsonObject){
        Long roleId = jsonObject.getLong("roleId");
        Assert.notNull(roleId,"角色不为空");
        List permissionIds = jsonObject.getJSONArray("permissionIds").toJavaList(Long.class);
        roleService.insertPermission(roleId,permissionIds);
        return JsonTemplate.success(null,"角色分配权限成功");
    }



}
