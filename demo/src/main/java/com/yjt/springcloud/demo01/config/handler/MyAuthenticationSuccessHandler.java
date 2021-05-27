package com.yjt.springcloud.demo01.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yjt.springcloud.demo01.config.properties.SecurityProperties;
import com.yjt.springcloud.demo01.enums.LoginType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className MyAuthenticationSuccessHandler
 * @description 认证成功处理器
 * @author YM
 * @date 2021-05-25 15:49
 * @version V1.0
 * @since 1.0
 **/
@Slf4j
@Component
public class MyAuthenticationSuccessHandler  extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        logger.info("认证成功");
        LoginType loginType = securityProperties.getBrowser().getLoginType();
        //重定向
        if(LoginType.REDIRECT.equals(loginType)){
            super.onAuthenticationSuccess(request,response,authentication);
        }else{
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }
    }
}
