package com.yjt.springcloud.demo01.validatecode;

import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.yjt.springcloud.demo01.validatecode.bean.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @ClassName DefaultImageCaptcha
 * @Description 默认验证码无过期时间,不缓存
 * @Author: YM
 * @Version V1.0
 * @Since V1.0
 * @Date: 2019-09-18 17:29
 **/
@Component
public class DefaultImageCaptcha implements Captcha {

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    private CaptchaService captchaService;

    @Override
    public ValidateCode render() throws IOException {
        byte[] captchaChallengeAsJpeg = null;
        ValidateCode validateCode = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {

            String captchaId = httpServletRequest.getSession().getId();
            BufferedImage challenge = (BufferedImage) captchaService.getChallengeForID(captchaId,
                    httpServletRequest.getLocale());

            // a jpeg encoder
            JPEGImageEncoder jpegEncoder =
                    JPEGCodec.createJPEGEncoder(jpegOutputStream);
            jpegEncoder.encode(challenge);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
        } catch (CaptchaServiceException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

        // flush it in the response
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
        return validateCode;
    }


    @Override
    public boolean validate(String code) {
        String captchaId = httpServletRequest.getSession().getId();
        return captchaService.validateResponseForID(captchaId,code);
    }
}
