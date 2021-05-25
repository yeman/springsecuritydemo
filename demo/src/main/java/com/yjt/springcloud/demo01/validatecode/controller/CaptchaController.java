package com.yjt.springcloud.demo01.validatecode.controller;

import com.yjt.springcloud.demo01.common.Result;
import com.yjt.springcloud.demo01.validatecode.Captcha;
import com.yjt.springcloud.demo01.validatecode.CustomImageCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @ClassName ValidateCodeController
 * @Description 验证码测试
 * @Author: YM
 * @Version V1.0
 * @Since V1.0
 * @Date: 2019-09-18 15:43
 **/
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private CustomImageCaptcha captcha;
    //private DefaultImageCaptcha captcha;

    /**
     * @description 验证码渲染
     * @author YM
     * @date 2021/5/24 17:43
     * @param
     * @return void
     */
    @GetMapping("/render")
    public void render() throws IOException {
        captcha.render();
    }

    /**
     * @description 验证码校验
     * @author YM
     * @date 2021/5/24 17:43
     * @param code
     * @return void
     */
    @PostMapping("/validate")
    public Result validate(@RequestParam String code) {
        return Result.success(captcha.validate(code));
    }

}
