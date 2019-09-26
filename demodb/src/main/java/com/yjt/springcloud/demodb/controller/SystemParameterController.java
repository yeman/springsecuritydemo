package com.yjt.springcloud.demodb.controller;

import com.yjt.springcloud.demodb.entity.SystemParameter;
import com.yjt.springcloud.demodb.service.SystemParameterSerivce;
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
 * ClassName: SystemParameterController
 * Date: 2019-09-23 21:34
 * author Administrator
 * version V1.0
 */
@RestController
@RequestMapping("/system/parameter")
public class SystemParameterController {

    @Autowired
    private SystemParameterSerivce systemParameterSerivce;

    /*
     * 参数新增
     * @author Administrator
     * @date 2019-09-23 21:42
     * @param systemParameter
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("insert")
    public JsonTemplate addParameter(@RequestBody SystemParameter systemParameter){
        return JsonTemplate.success(systemParameterSerivce.add(systemParameter),"参数添加成功");
    }

    /*
     * 参数修改
     * @author Administrator
     * @date 2019-09-23 21:42
     * @param systemParameter
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("update")
    public JsonTemplate updateParameter(@RequestBody SystemParameter systemParameter){
        return JsonTemplate.success(systemParameterSerivce.update(systemParameter),"参数修改成功");
    }

    /*
     * 参数查看
     * @author Administrator
     * @date 2019-09-23 21:44
     * @param id
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @GetMapping("get/{id}")
    public JsonTemplate getRole(@PathVariable Long id){
        return JsonTemplate.success(systemParameterSerivce.findById(id),"参数查询成功");
    }

    /*
     * 参数状态更新
     * @author Administrator
     * @date 2019-09-23 21:45
     * @param param
     * @return com.yjt.springcloud.demodb.web.JsonTemplate
     */
    @PostMapping("updateState")
    public JsonTemplate updateState(@RequestBody Map param){
        return JsonTemplate.success(systemParameterSerivce.updateStateById(param),"参数状态更新成功");
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
        systemParameterSerivce.deleteById(id);
        return JsonTemplate.success(null,"参数删除成功");
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
    public JsonTemplate page(SystemParameterQueryCriteria criteria, @PageableDefault(value = 10, sort = {"createTime"}, direction = Sort.Direction.ASC)Pageable pageable){
        return JsonTemplate.success(systemParameterSerivce.page(criteria,pageable),"参数分页查询成功");
    }
}

