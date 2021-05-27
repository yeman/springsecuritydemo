package com.yjt.springcloud.demo01.validatecode.bean;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @className ValidateCode
 * @description 验证码逻辑
 * @author YM
 * @date 2021-05-27 12:57
 * @version V1.0
 * @since 1.0
 **/
@Data
public class ValidateCode implements Serializable {
    private static final long serialVersionUID = 6687269769963562207L;

    private String code;

    /** 过期时间 */
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
