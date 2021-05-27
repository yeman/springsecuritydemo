package com.yjt.springcloud.demo01.validatecode.filter;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className ValidateCodeFilter
 * @description 验证码过滤器
 * @author YM
 * @date 2021-05-27 11:22
 * @version V1.0
 * @since 1.0
 **/
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
    }
}
