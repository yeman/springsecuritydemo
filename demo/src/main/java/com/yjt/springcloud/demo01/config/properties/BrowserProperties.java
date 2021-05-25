package com.yjt.springcloud.demo01.config.properties;

import com.yjt.springcloud.demo01.enums.LoginType;
import lombok.Data;

/**
 * @className BrowserProperties
 * @description 浏览器登录配置参数
 * @author YM
 * @date 2021-05-25 14:24
 * @version V1.0
 * @since 1.0
 **/
@Data
public class BrowserProperties {

    private String loginPage = "/login.html";
    private LoginType loginType = LoginType.REDIRECT;
}
