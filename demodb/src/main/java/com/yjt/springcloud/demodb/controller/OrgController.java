package com.yjt.springcloud.demodb.controller;

import com.yjt.springcloud.demodb.entity.Org;
import com.yjt.springcloud.demodb.service.OrgService;
import com.yjt.springcloud.demodb.web.JsonTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 机构
 * ClassName: OrgController
 * Date: 2019-08-31 21:54
 * author Administrator
 * version V1.0
 */
@Slf4j
@RestController
@RequestMapping("org")
public class OrgController {

    @Autowired
    private OrgService orgService;
    
    
  /*
   * 机构添加
   * @author Administrator
   * @date 2019-08-31 22:57
   * @param org
   * @return com.yjt.springcloud.demodb.web.JsonTemplate
   */
    @PostMapping("insert")
    public JsonTemplate addOrg(@RequestBody Org org){
        return JsonTemplate.success(orgService.insert(org),"添加成功");
    }

    /*
     * 机构删除
     * @author Administrator
     * @date 2019-08-31 23:26
     * @param org
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("delete/{orgId}")
    public JsonTemplate delOrg(@PathVariable Long orgId){
        orgService.delete(orgId);
        return JsonTemplate.success(null,"删除成功");
    }

    /*
     * 根据id查询
     * @author Administrator
     * @date 2019-09-01 21:07
     * @param orgId
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @GetMapping("get/{orgId}")
    public JsonTemplate get(@PathVariable Long orgId){
        return JsonTemplate.success(orgService.findById(orgId),"查询成功");
    }

    /*
     * 机构修改
     * @author Administrator
     * @date 2019-09-01 20:03
     * @param org
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("update")
    public JsonTemplate updateOrg(@RequestBody  Org org){
        orgService.updateOrg(org);
        return JsonTemplate.success(null,"修改成功");
    }

    /*
     * 机构树
     * @author Administrator
     * @date 2019-09-01 20:07
     * @param
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("tree")
    public JsonTemplate tree(@RequestBody Map param){
        return JsonTemplate.success(orgService.tree(param),"查询成功");
    }

}
