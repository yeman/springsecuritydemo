package com.yjt.springcloud.demodb.validateCode;

import com.yjt.springcloud.demodb.enums.ValidateTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
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
        if(ValidateTypeEnum.PIC.getKey().equals(validateType)){

        }else{

        }
    }
}
