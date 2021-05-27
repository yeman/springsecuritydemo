package com.yjt.springcloud.demo01.controller;

import com.yjt.springcloud.demo01.config.properties.SecurityProperties;
import com.yjt.springcloud.demo01.constant.SecurityConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private SecurityProperties securityProperties;

    @GetMapping({"/index", "/home"})
    public String index(){
        return "index";
    }

    @RequestMapping({"/login.html","/{loginPage}" })
    public String login(@PathVariable(required = false) String loginPage){
        if(StringUtils.isNotBlank(loginPage)){
            return securityProperties.getBrowser().getLoginPage();
        }else{
            return SecurityConstant.DEFAULT_LOGIN_PAGE;
        }
    }

}
