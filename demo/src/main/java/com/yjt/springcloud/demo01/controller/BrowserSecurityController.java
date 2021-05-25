package com.yjt.springcloud.demo01.controller;

import com.yjt.springcloud.demo01.common.Result;
import com.yjt.springcloud.demo01.config.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className BrowserSecurityController
 * @description 浏览器登录
 * @author YM
 * @date 2021-05-25 14:32
 * @version V1.0
 * @since 1.0
 **/
@Slf4j
@RestController
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class BrowserSecurityController {

    @Autowired
    private SecurityProperties securityProperties;


    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private RequestCache requestCache = new HttpSessionRequestCache();


    /**
     * @description 需要认证的逻辑
     * @author YM
     * @date 2021/5/25 15:03
     * @param request
     * @param response
     * @return com.yjt.springcloud.demo01.common.Result
     */
    @RequestMapping("/authentication/require")
    public Result requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            log.debug("引发跳转的 url={}", redirectUrl);
            if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
                String loginPage = securityProperties.getBrowser().getLoginPage();
                redirectStrategy.sendRedirect(request, response, loginPage);
            }
        }
        return Result.failed(Result.SESSION_OUT,"访问的服务需要身份认证，请引导用户到登录页");
    }

}
