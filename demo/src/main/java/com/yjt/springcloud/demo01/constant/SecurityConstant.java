package com.yjt.springcloud.demo01.constant;

/**
 * @className SecurityConstant
 * @description TODO
 * @author YM
 * @date 2021-05-26 10:55
 * @version V1.0
 * @since 1.0
 **/
public interface SecurityConstant {

    /**
     * 默认登录页面
     **/
    public String DEFAULT_LOGIN_PAGE = "/login.html";

    /**
     * 未授权时访问url
     **/
    public String DEFAULT_UNAUTHENTICATED_URL = "/authentication/require";

    /**
     * 默认的用户名密码登录请求处理url
     **/
    public String DEFAULT_LOGIN_PROCESSING_FORM_URL = "/login";

    /**
     * 验证码url
     **/
    public String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/captcha";


    /**
     * 验证图片验证码参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

    /**
     * 验证短信验证码参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
}
