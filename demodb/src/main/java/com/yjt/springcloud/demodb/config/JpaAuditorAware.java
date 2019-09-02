package com.yjt.springcloud.demodb.config;

import com.yjt.springcloud.demodb.entity.UserDetailVo;
import org.hibernate.usertype.UserVersionType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 审计字段
 * ClassName: JpaAuditorAware
 * Date: 2019-09-01 21:55
 * author Administrator
 * version V1.0
 */
@Configuration
public class JpaAuditorAware implements AuditorAware {

    @Value("root")
    private String operator;

    @Override
    public Optional getCurrentAuditor() {
        UserDetailVo userDetailVo;
        try {
            userDetailVo = (UserDetailVo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return Optional.ofNullable(userDetailVo.getUsername());
        } catch (Exception e) {
            return Optional.of(operator);
        }
    }


}
