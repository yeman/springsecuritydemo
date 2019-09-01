package com.yjt.springcloud.demodb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 审计字段
 * ClassName: OperatorAuditorAware
 * Date: 2019-09-01 21:55
 * author Administrator
 * version V1.0
 */
public class OperatorAuditorAware implements AuditorAware<String> {

    @Value("root")
    private String operator;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(operator);
    }
}
