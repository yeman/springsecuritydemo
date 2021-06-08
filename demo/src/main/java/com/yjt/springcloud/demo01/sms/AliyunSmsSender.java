package com.yjt.springcloud.demo01.sms;

import org.springframework.stereotype.Component;

/**
 * @className AliyunSmsSender
 * @description TODO
 * @author YM
 * @date 2021-06-03 11:09
 * @version V1.0
 * @since 1.0
 **/
@Component
public class AliyunSmsSender implements SmsCodeSender {
    @Override
    public boolean send(String mobile, String content) {
        return false;
    }
}
