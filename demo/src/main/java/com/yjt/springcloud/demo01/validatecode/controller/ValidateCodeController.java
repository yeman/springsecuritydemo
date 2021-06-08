package com.yjt.springcloud.demo01.validatecode.controller;

import com.yjt.springcloud.demo01.constant.SecurityConstant;
import com.yjt.springcloud.demo01.validatecode.processor.ValidateCodeProcessor;
import com.yjt.springcloud.demo01.validatecode.support.ValidateCodeProcessorSupport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @className ValidateCodeController
 * @description 验证码
 * @author YM
 * @date 2021-06-07 15:28
 * @version V1.0
 * @since 1.0
 **/
@RestController
@AllArgsConstructor
public class ValidateCodeController {

    private final ValidateCodeProcessorSupport validateCodeProcessorSupport;


    @RequestMapping(SecurityConstant.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createCode(ServletWebRequest request, @PathVariable String type) throws Exception {
        validateCodeProcessorSupport.findValidateCodeProcessor(type).create(new ServletWebRequest(request.getRequest(), request.getResponse()));
    }
}
