package com.yjt.springcloud.demo01.config.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @className MyUserDetailService
 * @description 自定义验证逻辑
 * @author YM
 * @date 2021-05-26 16:28
 * @version V1.0
 * @since 1.0
 **/
@Component
public class MyUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1 模拟从数据库查询
        //2 明文密码加密,得到加密后的密文
        String passwordEncode = "$2a$10$VPQqu37rJDUl4lumKumhZux4BAXZiVzMCvvgHiu.1.v0NU/BWJD7u";
        User user = new User("root",passwordEncode, Arrays.asList(new SimpleGrantedAuthority("admin")));
        return user;
    }
}
