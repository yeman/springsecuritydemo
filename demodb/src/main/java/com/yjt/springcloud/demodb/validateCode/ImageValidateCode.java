package com.yjt.springcloud.demodb.validateCode;

import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * TODO
 * ClassName: ImageValidateCode
 * Date: 2019-10-23 22:37
 * author Administrator
 * version V1.0
 */
@Data
public class ImageValidateCode extends ValidateCode {
    private BufferedImage image;

    public ImageValidateCode(BufferedImage image, String code, Long expireTime) {
        super(code, expireTime);
        this.image = image;

    }
}
