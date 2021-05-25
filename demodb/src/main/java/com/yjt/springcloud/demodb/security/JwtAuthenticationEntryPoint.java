package com.yjt.springcloud.demodb.security;

import cn.hutool.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO
 * ClassName: JwtAuthenticationEntryPoint
 * Date: 2019-10-21 21:21
 * author Administrator
 * version V1.0
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.sendError(HttpStatus.HTTP_NOT_AUTHORITATIVE, e == null ? "Unauthorized" : e.getMessage());
    }
}
