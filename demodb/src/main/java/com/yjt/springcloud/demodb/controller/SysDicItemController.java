package com.yjt.springcloud.demodb.controller;

import com.alibaba.fastjson.JSONArray;
import com.yjt.springcloud.demodb.entity.SysDic;
import com.yjt.springcloud.demodb.entity.SysDicItem;
import com.yjt.springcloud.demodb.service.SysDicItemService;
import com.yjt.springcloud.demodb.service.SysDicService;
import com.yjt.springcloud.demodb.service.condition.SysDicItemQueryCriteria;
import com.yjt.springcloud.demodb.service.condition.SystemParameterQueryCriteria;
import com.yjt.springcloud.demodb.web.JsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * TODO
 * ClassName: SysDicItemController
 * Date: 2019-09-23 23:23
 * author Administrator
 * version V1.0
 */
@RestController
@RequestMapping("/system/sysDicItem")
public class SysDicItemController {
    @Autowired
    private SysDicItemService sysDicItemService;

    /*
     * 字典项新增
     * @author Administrator
     * @date 2019-09-23 21:42
     * @param sysDicItem
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("insert")
    public JsonTemplate insert(@RequestBody SysDicItem sysDicItem){
        return JsonTemplate.success(sysDicItemService.add(sysDicItem),"字典项添加成功");
    }

    /*
     * 字典项修改
     * @author Administrator
     * @date 2019-09-23 21:42
     * @param sysDicItem
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("update")
    public JsonTemplate update(@RequestBody SysDicItem sysDicItem){
        return JsonTemplate.success(sysDicItemService.update(sysDicItem),"字典项修改成功");
    }

    /*
     * 字典项查看
     * @author Administrator
     * @date 2019-09-23 21:44
     * @param id
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @GetMapping("get/{id}")
    public JsonTemplate get(@PathVariable Long id){
        return JsonTemplate.success(sysDicItemService.findById(id),"字典项查询成功");
    }

    /*
     * 字典状态更新
     * @author Administrator
     * @date 2019-09-23 21:45
     * @param param
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("updateState")
    public JsonTemplate updateState(@RequestBody Map param){
        return JsonTemplate.success(sysDicItemService.updateStateById(param),"字典项更新成功");
    }

    /*
     * 删除
     * @author Administrator
     * @date 2019-09-23 22:53
     * @param id
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("delete/{id}")
    public JsonTemplate delete(@PathVariable Long id){
        sysDicItemService.deleteById(id);
        return JsonTemplate.success(null,"字典项删除成功");
    }

    /*
     * 批量删除
     * @author Administrator
     * @date 2019-09-25 21:58
     * @param jsonArray
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("deleteByIds")
    public JsonTemplate deleteByIds(@RequestBody JSONArray jsonArray){
        sysDicItemService.deleteByIds(jsonArray.toJavaList(Long.class));
        return JsonTemplate.success(null,"字典项删除成功");
    }

    /*
     * 分页
     * @author Administrator
     * @date 2019-09-23 22:53
     * @param criteria
     * @param pageable
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("page")
    public JsonTemplate page(SysDicItemQueryCriteria criteria, @PageableDefault(value = 10, sort = {"createTime"}, direction = Sort.Direction.ASC) Pageable pageable){
        return JsonTemplate.success(sysDicItemService.page(criteria,pageable),"字典项分页查询成功");
    }
}
