package com.yjt.springcloud.demodb.config;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.yjt.springcloud.demodb.web.JsonTemplate;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常
 * ClassName: GlobaExpcetion
 * Date: 2019-09-01 0:54
 * author Administrator
 * version V1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExpcetionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JsonTemplate errorHandler(HttpServletRequest request, HttpServletResponse response,Exception e){
        String stackError = ExceptionUtil.stacktraceToString(e);
        log.error("errorHandler info:{}",stackError);
        JsonTemplate jsonTemplate = JsonTemplate.failed(e.getMessage(),stackError);
        return jsonTemplate;
    }
}
