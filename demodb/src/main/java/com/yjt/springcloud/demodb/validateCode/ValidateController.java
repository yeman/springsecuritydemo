package com.yjt.springcloud.demodb.validateCode;

import com.baomidou.kaptcha.GoogleKaptcha;
import com.baomidou.kaptcha.Kaptcha;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.yjt.springcloud.demodb.enums.ValidateTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

/**
 * 验证码
 * ClassName: ValidateController
 * Date: 2019-10-22 22:41
 * author Administrator
 * version V1.0
 */
@RestController
@RequestMapping("/kaptcha")
public class ValidateController {
    @Autowired
    private ValidateCodeConfig validateCodeConfig;

    @PostMapping("render")
    public void render(){
        String validateType = validateCodeConfig.getType();
        if(ValidateTypeEnum.IMAGE.getKey().equals(validateType)){

        }else{

        }
    }
    @PostMapping("/valid")
    public void validDefaultTime(@RequestParam String code) {
    }

    @PostMapping("/validTime")
    public void validWithTime(@RequestParam String code) {

    }
}
