package com.yjt.springcloud.demo01.validatecode;

import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @ClassName DefaultImageCaptcha
 * @Description TODO
 * @Author: YM
 * @Version V1.0
 * @Since V1.0
 * @Date: 2019-09-18 17:29
 **/
public class DefaultImageCaptcha implements Captcha {

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    private CaptchaService captchaService;

    @Override
    public void render() throws IOException {
        byte[] captchaChallengeAsJpeg = null;
        // the output stream to render the captcha image as jpeg into
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // get the session id that will identify the generated captcha.
            //the same id must be used to validate the response, the session id is a good candidate!
            String captchaId = httpServletRequest.getSession().getId();
            // call the ImageCaptchaService getChallenge method
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
    }


    @Override
    public boolean validate(String code) {
        String captchaId = httpServletRequest.getSession().getId();
        return captchaService.validateResponseForID(captchaId,code);
    }
}
