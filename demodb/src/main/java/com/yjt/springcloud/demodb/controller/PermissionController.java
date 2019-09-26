package com.yjt.springcloud.demodb.controller;

import com.yjt.springcloud.demodb.entity.Permission;
import com.yjt.springcloud.demodb.service.PerssionService;
import com.yjt.springcloud.demodb.web.JsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 权限
 * ClassName: PermissionController
 * Date: 2019-09-19 20:27
 * author Administrator
 * version V1.0
 */
@RestController
@RequestMapping("system/permission")
public class PermissionController {
    @Autowired
    private PerssionService perssionService;

    /*
     * 权限新增
     * @author Administrator
     * @date 2019-09-19 20:32
     * @param permission
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("insert")
    public JsonTemplate insert(@RequestBody Permission permission){
        return JsonTemplate.success(perssionService.insert(permission),"添加成功");
    }
    /*
     * 权限删除
     * @author Administrator
     * @date 2019-08-31 23:26
     * @param groupId
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("delete/{permissionId}")
    public JsonTemplate delPermission(@PathVariable Long permissionId){
        perssionService.delete(permissionId);
        return JsonTemplate.success(null,"删除成功");
    }
    /*
     * 根据id查询
     * @author Administrator
     * @date 2019-09-01 21:07
     * @param groupId
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @GetMapping("get/{permissionId}")
    public JsonTemplate get(@PathVariable Long permissionId){
        return JsonTemplate.success(perssionService.findById(permissionId),"查询成功");
    }
    /*
     * 机构修改
     * @author Administrator
     * @date 2019-09-01 20:03
     * @param group
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("update")
    public JsonTemplate updatePermission(@RequestBody Permission permission){
        return JsonTemplate.success( perssionService.updatePermission(permission),"修改成功");
    }

    /*
     * 树
     * @author Administrator
     * @date 2019-09-19 20:57
     * @param param
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("tree")
    public JsonTemplate tree(@RequestBody Map param){
        return JsonTemplate.success(perssionService.tree(param),"查询成功");
    }

}
