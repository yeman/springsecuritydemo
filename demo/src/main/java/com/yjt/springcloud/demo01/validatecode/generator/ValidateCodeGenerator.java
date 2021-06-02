package com.yjt.springcloud.demo01.validatecode.generator;

import com.yjt.springcloud.demo01.validatecode.bean.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @className ValidateCodeGenerator
 * @description 验证码生成
 * @author YM
 * @date 2021-06-02 15:20
 * @version V1.0
 * @since 1.0
 **/
public interface ValidateCodeGenerator {

    /**
     * 验证码生成
     **/
    ValidateCode generate(ServletWebRequest request);
}
