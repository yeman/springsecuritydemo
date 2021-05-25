package com.yjt.springcloud.demo01.config;

import com.yjt.springcloud.demo01.config.handler.MyAuthenticationFailureHandler;
import com.yjt.springcloud.demo01.config.handler.MyAuthenticationSuccessHandler;
import com.yjt.springcloud.demo01.config.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    /**
     *  index首页免权限访问
     *  表单登录页面 /login
     *  其余访问都需要认证
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String redirectUrl = securityProperties.getBrowser().getLoginPage();
        http.authorizeRequests()
               .antMatchers("/","/index","/kaptcha/*","/captcha/*").permitAll()
               .and().formLogin().loginPage("/authentication/require")
                                 .loginProcessingUrl("/authentication/form")
                                 .successHandler(myAuthenticationSuccessHandler)
                                 .failureHandler(myAuthenticationFailureHandler)
                //不需要权限认证
               .and().logout().permitAll()
               .and().authorizeRequests().antMatchers("/authentication/require",redirectUrl).permitAll()
               .anyRequest().authenticated()
               .and().csrf().disable();
    }
    //webjar中的静态资源不被拦截
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/img/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       //用户 内存认证管理器
        auth.inMemoryAuthentication().withUser("root").password("root").roles("admin");
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
