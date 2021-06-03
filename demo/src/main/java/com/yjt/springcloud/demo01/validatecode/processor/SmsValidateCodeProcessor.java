package com.yjt.springcloud.demo01.validatecode.processor;

import com.yjt.springcloud.demo01.sms.SmsCodeSender;
import com.yjt.springcloud.demo01.validatecode.bean.ValidateCode;
import com.yjt.springcloud.demo01.validatecode.enums.ValidateCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @className SmsValidateCodeProcessor
 * @description TODO
 * @author YM
 * @date 2021-06-02 09:06
 * @version V1.0
 * @since 1.0
 **/
@Component
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    public void send(ServletWebRequest request, ValidateCode validateCode) {
        String mobile = request.getParameter(ValidateCodeEnum.SMS.getParameterName());
        smsCodeSender.send(mobile, validateCode.getCode());
    }
}
