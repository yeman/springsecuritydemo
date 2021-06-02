package com.yjt.springcloud.demo01.validatecode.enums;

import com.yjt.springcloud.demo01.constant.SecurityConstant;
import lombok.Getter;

/**
 * @className ValidateCodeEnum
 * @description 验证码枚举
 * @author YM
 * @date 2021-05-27 13:06
 * @version V1.0
 * @since 1.0
 **/
@Getter
public enum ValidateCodeEnum {

    IMAGE("image", "图片验证码") {
        @Override
        public String getParameterName() {
            return SecurityConstant.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    },
    SMS("sms", "短信验证码") {
        @Override
        public String getParameterName() {
            return SecurityConstant.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    };

    private String type;
    private String remark;


    ValidateCodeEnum(String type, String remark) {
        this.type = type;
        this.remark = remark;
    }

    public static ValidateCodeEnum ofType(String type) {
        ValidateCodeEnum[] enums = ValidateCodeEnum.values();
        for (ValidateCodeEnum validateCodeEnum : enums) {
            if (validateCodeEnum.getType().equalsIgnoreCase(type)) {
                return validateCodeEnum;
            }
        }
        return null;
    }

    public abstract String getParameterName();

}
