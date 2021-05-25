package com.yjt.springcloud.demodb.validateCode;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码处理器
 * ClassName: ValidateProcessor
 * Date: 2019-10-22 22:41
 * author Administrator
 * version V1.0
 */
public interface ValidateProcessor {

    public void sendCode(HttpServletRequest request);

    public boolean validate(String code);

}
