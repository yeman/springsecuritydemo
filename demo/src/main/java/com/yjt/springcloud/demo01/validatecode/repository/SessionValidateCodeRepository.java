package com.yjt.springcloud.demo01.validatecode.repository;

import com.yjt.springcloud.demo01.validatecode.bean.ValidateCode;
import com.yjt.springcloud.demo01.validatecode.enums.ValidateCodeEnum;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @className SessionValidateCodeRepository
 * @description TODO
 * @author YM
 * @date 2021-05-27 14:47
 * @version V1.0
 * @since 1.0
 **/
public class SessionValidateCodeRepository implements ValidateCodeRepository {
    private SessionStrategy
    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeEnum validateCodeEnum) {

    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeEnum validateCodeType) {
        return null;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeEnum validateCodeType) {

    }
}
