package com.yjt.springcloud.demo01.validatecode.filter;

import com.google.common.collect.Maps;
import com.yjt.springcloud.demo01.config.handler.MyAuthenticationFailureHandler;
import com.yjt.springcloud.demo01.config.properties.SecurityProperties;
import com.yjt.springcloud.demo01.constant.SecurityConstant;
import com.yjt.springcloud.demo01.validatecode.enums.ValidateCodeEnum;
import com.yjt.springcloud.demo01.validatecode.exception.ValidateCodeException;
import com.yjt.springcloud.demo01.validatecode.support.ValidateCodeProcessorSupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

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

    @Autowired
    private ValidateCodeProcessorSupport validateCodeProcessorSupport;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private SecurityProperties securityProperties;

    private Map<String, ValidateCodeEnum> validateCodeUrlEnumMap = Maps.newHashMap();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ValidateCodeEnum type = getValidateCodeType(request);
        if (type != null) {
            logger.debug("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
            try {
                validateCodeProcessorSupport.findValidateCodeProcessor(type).validate(new ServletWebRequest(request, response));
                logger.debug("验证码校验通过");
            } catch (ValidateCodeException exception) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private ValidateCodeEnum getValidateCodeType(HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        if (!StringUtils.equalsIgnoreCase(method, HttpMethod.GET.name())) {
            Optional<Map.Entry<String, ValidateCodeEnum>> optional = validateCodeUrlEnumMap.entrySet().stream().filter(entry -> antPathMatcher.match(entry.getKey(),requestUrl)).findFirst();
            if (optional.isPresent()) {
                return optional.get().getValue();
            }
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        //初始化
        validateCodeUrlEnumMap.put(SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeEnum.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeEnum.IMAGE);

        validateCodeUrlEnumMap.put(SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeEnum.SMS);
        addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeEnum.SMS);
    }


    public void addUrlToMap(String url , ValidateCodeEnum validateCodeEnum){
        if(StringUtils.isNotBlank(url)){
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(url,",");
            Stream.of(urls).forEach(e ->{
                validateCodeUrlEnumMap.put(url,validateCodeEnum);
            });
        }
    }
}
