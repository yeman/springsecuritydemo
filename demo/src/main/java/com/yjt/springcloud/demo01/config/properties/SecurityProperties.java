package com.yjt.springcloud.demo01.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @className SecurityProperties
 * @description TODO
 * @author YM
 * @date 2021-05-25 14:22
 * @version V1.0
 * @since 1.0
 **/
@Component
@Data
@ConfigurationProperties(prefix = "yjt.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

}
