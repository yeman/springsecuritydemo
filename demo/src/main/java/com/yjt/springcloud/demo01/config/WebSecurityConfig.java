package com.yjt.springcloud.demo01.config;

import com.yjt.springcloud.demo01.config.configurerAdapter.ValidateCodeConfigureAdapter;
import com.yjt.springcloud.demo01.config.handler.MyAuthenticationFailureHandler;
import com.yjt.springcloud.demo01.config.handler.MyAuthenticationSuccessHandler;
import com.yjt.springcloud.demo01.config.properties.SecurityProperties;
import com.yjt.springcloud.demo01.config.service.MyUserDetailService;
import com.yjt.springcloud.demo01.constant.SecurityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.yjt.springcloud.demo01.constant.SecurityConstant.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

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
        http.apply(validateCodeConfigureAdapter)
             .and()
             .formLogin()
            .loginPage(DEFAULT_UNAUTHENTICATED_URL)
            .loginProcessingUrl(DEFAULT_LOGIN_PROCESSING_FORM_URL)
            .successHandler(myAuthenticationSuccessHandler)
            .failureHandler(myAuthenticationFailureHandler)

            .and().logout().permitAll().and()
            .authorizeRequests()
            .antMatchers(DEFAULT_VALIDATE_CODE_URL_PREFIX + "/**",
                    DEFAULT_UNAUTHENTICATED_URL+"/**",
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
