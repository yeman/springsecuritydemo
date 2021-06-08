package com.yjt.springcloud.demo01.config.configurerAdapter;

import com.yjt.springcloud.demo01.validatecode.filter.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

/**
 * @className ValidateCodeConfigureAdapter
 * @description TODO
 * @author YM
 * @date 2021-05-27 11:35
 * @version V1.0
 * @since 1.0
 **/
@Component
public class ValidateCodeConfigureAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Override
    public void configure(HttpSecurity builder){
        builder.addFilterBefore(validateCodeFilter, AbstractPreAuthenticatedProcessingFilter.class);
    }
}
