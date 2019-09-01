package com.yjt.springcloud.demodb.config;

import com.yjt.springcloud.demodb.orm.BaseRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * JPA 配置类
 * ClassName: JpaRepositoryConfig
 * Date: 2019-09-01 23:38
 * author Administrator
 * version V1.0
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.yjt.springcloud.demodb.repository"},repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class JpaRepositoryConfig {
}
