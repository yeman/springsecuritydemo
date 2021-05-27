package com.yjt.springcloud.demo01.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yjt.springcloud.demo01.config.properties.SecurityProperties;
import com.yjt.springcloud.demo01.enums.LoginType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className MyAuthenticationFailureHandler
 * @description TODO
 * @author YM
 * @date 2021-05-25 15:54
 * @version V1.0
 * @since 1.0
 **/
@Slf4j
@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        LoginType loginType = securityProperties.getBrowser().getLoginType();
        logger.info("认证失败");
        //重定向
        if(LoginType.REDIRECT.equals(loginType)){
            super.onAuthenticationFailure(request,response,exception);
        }else{
            response.setStatus(500);
            //将 登录失败 信息打包成json格式返回
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(exception));
        }
    }
}
