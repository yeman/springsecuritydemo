package com.yjt.springcloud.demo01.config.properties;

import lombok.Data;

/**
 * @className ValidateCodeProperties
 * @description TODO
 * @author YM
 * @date 2021-06-04 16:43
 * @version V1.0
 * @since 1.0
 **/
@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();

}
