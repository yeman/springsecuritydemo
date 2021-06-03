package com.yjt.springcloud.demo01.sms;

/**
 * TODO
 * ClassName: SmsCodeSender
 * Date: 2021-06-02 23:48
 * author Administrator
 * version V1.0
 */
public interface SmsCodeSender {
    public boolean send(String mobile, String content);
}
