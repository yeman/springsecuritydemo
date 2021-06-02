package com.yjt.springcloud.demo01.validatecode.repository;

import com.yjt.springcloud.demo01.validatecode.bean.ValidateCode;
import com.yjt.springcloud.demo01.validatecode.enums.ValidateCodeEnum;
import org.springframework.lang.Nullable;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Optional;

/**
 * @className SessionValidateCodeRepository
 * @description TODO
 * @author YM
 * @date 2021-05-27 14:47
 * @version V1.0
 * @since 1.0
 **/
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private static final String VALIDATE_SESSION_KEY = "SESSION_KEY_CODE_";

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeEnum validateCodeEnum) {
        sessionStrategy.setAttribute(request, buildKey(request, validateCodeEnum), code);
    }

    @Nullable
    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeEnum validateCodeEnum) {
        Optional optional = Optional.ofNullable(sessionStrategy.getAttribute(request, buildKey(request, validateCodeEnum)));
        if (optional.isPresent()) {
            return (ValidateCode) optional.get();
        }
        return null;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeEnum validateCodeEnum) {
        sessionStrategy.removeAttribute(request, buildKey(request, validateCodeEnum));
    }

    @Override
    public String buildKey(ServletWebRequest request, ValidateCodeEnum validateCodeEnum) {
        return VALIDATE_SESSION_KEY + validateCodeEnum.getType().toUpperCase();
    }
}
