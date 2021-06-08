package com.yjt.springcloud.demo01.config;

import com.yjt.springcloud.demo01.config.configurerAdapter.ValidateCodeConfigureAdapter;
import com.yjt.springcloud.demo01.config.properties.SecurityProperties;
import com.yjt.springcloud.demo01.config.service.MyUserDetailService;
import com.yjt.springcloud.demo01.constant.SecurityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static com.yjt.springcloud.demo01.constant.SecurityConstant.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private ValidateCodeConfigureAdapter validateCodeConfigureAdapter;

    /**
     *  index首页免权限访问
     *  表单登录页面 /login
     *  其余访问都需要认证
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
            .loginPage(DEFAULT_UNAUTHENTICATED_URL)
            .loginProcessingUrl(DEFAULT_LOGIN_PROCESSING_FORM_URL)
            .successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHandler)

            .and().apply(validateCodeConfigureAdapter)
            .and().logout().permitAll().and()
            .authorizeRequests()
            .antMatchers(DEFAULT_UNAUTHENTICATED_URL,
                    DEFAULT_VALIDATE_CODE_URL_PREFIX + "/**",
                    SecurityConstant.DEFAULT_LOGIN_PAGE,
                    securityProperties.getBrowser().getLoginPage())
            .permitAll()
            .anyRequest().authenticated()
            .and().userDetailsService(myUserDetailService)
            .csrf().disable();


    }

    //webjar中的静态资源不被拦截
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/img/**");
    }

   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //用户 内存认证管理器
        auth.inMemoryAuthentication().withUser("root").password("root").roles("admin");
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
