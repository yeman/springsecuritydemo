package com.yjt.springcloud.demo01.validatecode.support;

import com.yjt.springcloud.demo01.validatecode.enums.ValidateCodeEnum;
import com.yjt.springcloud.demo01.validatecode.exception.ValidateCodeException;
import com.yjt.springcloud.demo01.validatecode.processor.ValidateCodeProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * @className ValidateCodeProcessorSupport
 * @description TODO
 * @author YM
 * @date 2021-06-03 11:21
 * @version V1.0
 * @since 1.0
 **/
@Component
public class ValidateCodeProcessorSupport {

    private final Map<String, ValidateCodeProcessor> validateCodeProcessorMap;

    public ValidateCodeProcessorSupport(Map<String, ValidateCodeProcessor> validateCodeProcessorMap) {
        this.validateCodeProcessorMap = validateCodeProcessorMap;
    }

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeEnum validateCodeEnum) {
        return findValidateCodeProcessor(validateCodeEnum.getType());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String beanName = type + ValidateCodeProcessor.class.getSimpleName();
        Optional<Map.Entry<String, ValidateCodeProcessor>> optional = validateCodeProcessorMap.entrySet().stream().filter(p -> p.getKey().equalsIgnoreCase(beanName)).findFirst();
        if (!optional.isPresent()) {
            throw new ValidateCodeException("验证码处理器类型" + type + "不存在");
        }
        return optional.get().getValue();
    }
}
