package com.yjt.springcloud.demo01.validatecode.repository;

import com.yjt.springcloud.demo01.validatecode.bean.ValidateCode;
import com.yjt.springcloud.demo01.validatecode.enums.ValidateCodeEnum;
import com.yjt.springcloud.demo01.validatecode.exception.ValidateCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @className RedisValidateCodeRepositry
 * @description TODO
 * @author YM
 * @date 2021-05-27 13:46
 * @version V1.0
 * @since 1.0
 **/
@Component
@ConditionalOnBean(value = {RedisTemplate.class})
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${captcha.expireTime:50}")
    private long validateCodeExpire;

    private static final String VALIDATE_CODE_CACHE_KEY = "validateCode:";

    @Override
    public void save(ServletWebRequest request, ValidateCode validateCode, ValidateCodeEnum validateCodeEnum) {
        redisTemplate.opsForValue().set(buildKey(request, validateCodeEnum), validateCode.getCode(), validateCodeExpire, TimeUnit.SECONDS);
    }

    @Override
    public String buildKey(ServletWebRequest request, ValidateCodeEnum validateCodeEnum) {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new ValidateCodeException("请在请求头中携带deviceId参数");
        }
        return VALIDATE_CODE_CACHE_KEY + validateCodeEnum.getType().toString().toLowerCase() + ":" + deviceId;
    }

    @Nullable
    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeEnum validateCodeEnum) {
        Optional<Object> optional = Optional.ofNullable(redisTemplate.opsForValue().get(buildKey(request, validateCodeEnum)));
        if (optional.isPresent()) {
            return (ValidateCode) optional.get();
        }
        return null;

    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeEnum validateCodeEnum) {

    }
}
