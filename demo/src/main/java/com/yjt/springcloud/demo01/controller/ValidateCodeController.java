package com.yjt.springcloud.demo01.controller;

import com.baomidou.kaptcha.Kaptcha;
import com.yjt.springcloud.demo01.validatecode.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ValidateCodeController
 * @Description 验证码测试
 * @Author: YM
 * @Version V1.0
 * @Since V1.0
 * @Date: 2019-09-18 15:43
 **/
@RestController
@RequestMapping("kaptcha")
public class ValidateCodeController {
    @Autowired
    private Kaptcha kaptcha;

    @GetMapping("/render")
    public void render() {
        kaptcha.render();
    }

    @PostMapping("/valid")
    public void validDefaultTime(@RequestParam String code) {
        //default timeout 900 seconds
        kaptcha.validate(code);
    }
    @PostMapping("/validTime")
    public void validWithTime(@RequestParam String code) {
        kaptcha.validate(code, 60);
    }


}
