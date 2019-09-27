package com.yjt.springcloud.demodb.controller;

import com.alibaba.fastjson.JSONArray;
import com.yjt.springcloud.demodb.entity.SysDic;
import com.yjt.springcloud.demodb.service.SysDicService;
import com.yjt.springcloud.demodb.service.condition.SysDicQueryCriteria;
import com.yjt.springcloud.demodb.web.JsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * TODO
 * ClassName: DicController
 * Date: 2019-09-23 23:23
 * author Administrator
 * version V1.0
 */
@RestController
@RequestMapping("/system/sysDic")
public class SysDicController {
    @Autowired
    private SysDicService sysDicService;

    /*
     * 字典新增
     * @author Administrator
     * @date 2019-09-23 21:42
     * @param sysDic
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("insert")
    public JsonTemplate insert(@RequestBody SysDic sysDic){
        return JsonTemplate.success(sysDicService.add(sysDic),"字典添加成功");
    }

    /*
     * 字典修改
     * @author Administrator
     * @date 2019-09-23 21:42
     * @param sysDic
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("update")
    public JsonTemplate update(@RequestBody SysDic sysDic){
        return JsonTemplate.success(sysDicService.update(sysDic),"字典修改成功");
    }

    /*
     * 字典查看
     * @author Administrator
     * @date 2019-09-23 21:44
     * @param id
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @GetMapping("get/{id}")
    public JsonTemplate get(@PathVariable Long id){
        return JsonTemplate.success(sysDicService.findById(id),"参数查询成功");
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
        return JsonTemplate.success(sysDicService.updateStateById(param),"字典状态更新成功");
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
        sysDicService.deleteById(id);
        return JsonTemplate.success(null,"字典删除成功");
    }
    /*
     * 字典批量删除
     * @author Administrator
     * @date 2019-09-25 21:36
     * @param jsonArray
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("deleteByIds")
    public JsonTemplate deleteByIds(@RequestBody JSONArray jsonArray){
        sysDicService.deleteByIds(jsonArray.toJavaList(Long.class));
        return JsonTemplate.success(null,"字典删除成功");
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
    public JsonTemplate page(SysDicQueryCriteria criteria, @PageableDefault(value = 10, sort = {"createTime"}, direction = Sort.Direction.ASC) Pageable pageable){
        return JsonTemplate.success(sysDicService.page(criteria,pageable),"字典分页查询成功");
    }

    /*
     * 字典类别
     * @author Administrator
     * @date 2019-09-24 0:03
     * @param
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("typeList")
    public JsonTemplate typeList(){
        return JsonTemplate.success(sysDicService.typeList(),"字典类型查询成功");
    }
}
