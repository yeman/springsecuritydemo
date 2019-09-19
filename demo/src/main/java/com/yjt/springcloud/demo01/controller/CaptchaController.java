package com.yjt.springcloud.demo01.controller;

import com.baomidou.kaptcha.Kaptcha;
import com.yjt.springcloud.demo01.validatecode.Captcha;
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
@RequestMapping("captcha")
public class CaptchaController {
    @Autowired
    private Captcha captcha;

    @GetMapping("/render")
    public void render() throws IOException {
        captcha.render();
    }


}
