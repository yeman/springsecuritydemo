package com.yjt.springcloud.demo01.validatecode.processor;

import com.yjt.springcloud.demo01.validatecode.bean.ValidateCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @className ImageValidateCodeProcessor
 * @description TODO
 * @author YM
 * @date 2021-06-02 09:06
 * @version V1.0
 * @since 1.0
 **/
@Component
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor {

    @Override
    public void send(ServletWebRequest request, ValidateCode validateCode) {

    }
}
