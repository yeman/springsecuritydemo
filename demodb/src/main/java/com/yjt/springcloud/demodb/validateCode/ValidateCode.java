package com.yjt.springcloud.demodb.validateCode;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * TODO
 * ClassName: ValidateCode
 * Date: 2019-10-22 22:47
 * author Administrator
 * version V1.0
 */
@Data
public class ValidateCode {

    private String code;

    private LocalDateTime expiration;

    public ValidateCode(String code,Long expirationSecond){
        this.code = code;
        this.expiration = LocalDateTime.now().plusSeconds(expirationSecond);
    }

    public boolean isValidateCodeExpire(){
        return LocalDateTime.now().isAfter(expiration);
    }

}
