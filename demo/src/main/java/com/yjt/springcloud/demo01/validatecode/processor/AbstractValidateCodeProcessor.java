package com.yjt.springcloud.demo01.validatecode.processor;

import com.yjt.springcloud.demo01.validatecode.bean.ValidateCode;
import com.yjt.springcloud.demo01.validatecode.enums.ValidateCodeEnum;
import com.yjt.springcloud.demo01.validatecode.exception.ValidateCodeException;
import com.yjt.springcloud.demo01.validatecode.generator.ValidateCodeGenerator;
import com.yjt.springcloud.demo01.validatecode.repository.ValidateCodeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @className AbstractValidateCodeProcessor
 * @description 验证码处理器
 * @author YM
 * @date 2021-05-27 11:51
 * @version V1.0
 * @since 1.0
 **/
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    @Autowired
    private ValidateCodeRepository validateCodeRepository;

    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGeneratorMap;

    public static final String PROCESSOR_STR = "CodeProcessor";

    /**
     * @description 验证码创建
     * @author YM
     * @date 2021/6/2 9:15
     * @param request
     * @return void
     */
    @Override
    public void create(ServletWebRequest request) {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, )
    }

    @Override
    public void validate(ServletWebRequest servletWebRequest) {

    }

    /**
     * @description 生成逻辑
     * @author YM
     * @date 2021/6/2 15:22
     * @param request
     * @return C
     */
    private C generate(ServletWebRequest request) {
        ValidateCodeEnum validateCodeEnum = this.getValidateTypeEnum();
        if (validateCodeEnum == null) {
            throw new ValidateCodeException("未找到处理器类的类型");
        }
        String type = validateCodeEnum.getType().toLowerCase();
        String beanName = type + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap.get(beanName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException(String.format("验证码%s生成器类的不存在", beanName));
        }
        return (C) validateCodeGenerator.generate(request);
    }

    protected ValidateCodeEnum getValidateTypeEnum() {
        String prefix = StringUtils.substringBefore(this.getClass().getSimpleName(), PROCESSOR_STR);
        return ValidateCodeEnum.ofType(prefix);
    }

    private void save(C validateCode) {
    }

    public abstract void send(ServletWebRequest request, C validateCode);


}
