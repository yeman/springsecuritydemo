package com.yjt.springcloud.demodb;

import com.yjt.springcloud.demodb.config.JpaAuditorAware;
import com.yjt.springcloud.demodb.orm.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
public class DemoDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoDBApplication.class, args);
    }

}
