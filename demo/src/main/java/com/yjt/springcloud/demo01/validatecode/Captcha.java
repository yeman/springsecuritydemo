package com.yjt.springcloud.demo01.validatecode;

import com.yjt.springcloud.demo01.validatecode.bean.ValidateCode;

import java.io.IOException;

/**
 * @ClassName ImageCaptcha
 * @Description TODO
 * @Author: YM
 * @Version V1.0
 * @Since V1.0
 * @Date: 2019-09-18 16:20
 **/
public interface Captcha {

    int validateCodeExpire = 60;

    ValidateCode render() throws IOException;

    boolean validate(String code);

    default int getValidateCodeExpire() {
        return this.validateCodeExpire;
    }

}
