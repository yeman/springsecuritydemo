package com.yjt.springcloud.demo01.validatecode.processor;

import com.yjt.springcloud.demo01.validatecode.bean.ValidateCode;
import com.yjt.springcloud.demo01.validatecode.enums.ValidateCodeEnum;
import com.yjt.springcloud.demo01.validatecode.exception.ValidateCodeException;
import com.yjt.springcloud.demo01.validatecode.generator.ValidateCodeGenerator;
import com.yjt.springcloud.demo01.validatecode.repository.ValidateCodeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author YM
 * @version V1.0
 * @className AbstractValidateCodeProcessor
 * @description 验证码处理器
 * @date 2021-05-27 11:51
 * @since 1.0
 **/
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    @Autowired
    private ValidateCodeRepository validateCodeRepository;

    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGeneratorMap;

    public static final String PROCESSOR_STR = "ValidateCodeProcessor";

    /**
     * @param request
     * @return void
     * @description 验证码创建过程
     * @author YM
     * @date 2021/6/2 9:15
     */
    @Override
    public void create(ServletWebRequest request) {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    private void save(ServletWebRequest request, C validateCode) {
        validateCodeRepository.save(request, validateCode, getValidateTypeEnum());
    }

    @Override
    public void validate(ServletWebRequest servletWebRequest) {
        ValidateCodeEnum validateCodeEnum = getValidateTypeEnum();
        try {
            if (validateCodeEnum == null) {
                throw new ValidateCodeException("验证码类型不存在");
            }
            String codeInRequest = null;
            try {
                codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),   validateCodeEnum.getParameterName());
            } catch (ServletRequestBindingException e) {
                throw new ValidateCodeException("获取验证码参数失败");
            }

            C c = (C) validateCodeRepository.get(servletWebRequest, validateCodeEnum);
            if (c == null || (c!=null && c.isExpired())) {
                throw new ValidateCodeException("验证码失效");
            }

            if (!c.getCode().equalsIgnoreCase(codeInRequest)) {
                throw new ValidateCodeException("验证码错误");
            }

        } finally {
            validateCodeRepository.remove(servletWebRequest, validateCodeEnum);
        }

    }

    /**
     * @param request
     * @return C
     * @description 生成逻辑
     * @author YM
     * @date 2021/6/2 15:22
     */
    private C generate(ServletWebRequest request) {
        ValidateCodeEnum validateCodeEnum = this.getValidateTypeEnum();
        if (validateCodeEnum == null) {
            throw new ValidateCodeException("未找到处理器类的类型");
        }
        String type = validateCodeEnum.getType().toLowerCase();
        String beanName = type + ValidateCodeGenerator.class.getSimpleName();
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

    public abstract void send(ServletWebRequest request, C validateCode);


}
