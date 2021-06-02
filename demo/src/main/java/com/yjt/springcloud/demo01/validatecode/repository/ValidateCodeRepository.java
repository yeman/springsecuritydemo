package com.yjt.springcloud.demo01.validatecode.repository;

import com.yjt.springcloud.demo01.validatecode.bean.ValidateCode;
import com.yjt.springcloud.demo01.validatecode.enums.ValidateCodeEnum;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @className ValidateCodeRepository
 * @description 验证码处理接口
 * @author YM
 * @date 2021-05-27 12:56
 * @version V1.0
 * @since 1.0
 **/
public interface ValidateCodeRepository {

    void save(ServletWebRequest request, ValidateCode code, ValidateCodeEnum validateCodeEnum);

    @Nullable
    ValidateCode get(ServletWebRequest request, ValidateCodeEnum validateCodeEnum);

    void remove(ServletWebRequest request, ValidateCodeEnum validateCodeEnum);

    String buildKey(ServletWebRequest request, ValidateCodeEnum validateCodeEnum);
}
