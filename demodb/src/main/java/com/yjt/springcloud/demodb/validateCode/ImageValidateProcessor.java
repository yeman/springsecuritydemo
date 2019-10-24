package com.yjt.springcloud.demodb.validateCode;

import cn.hutool.core.util.ImageUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 * ClassName: ImageValidateProcessor
 * Date: 2019-10-22 22:50
 * author Administrator
 * version V1.0
 */
public class ImageValidateProcessor implements ValidateProcessor{

    private ValidateCodeConfig validateCodeConfig;

    public void sendCode(HttpServletRequest request){

    }

    @Override
    public boolean validate(String code) {
        return false;
    }

}
