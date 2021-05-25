package com.yjt.springcloud.demo01.validatecode;

import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.service.CaptchaServiceException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.yjt.springcloud.demo01.validatecode.exception.ValidateCodeExpireException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @className CustomImageCaptcha
 * @description 自定义验证码
 * @author YM
 * @date 2021-05-24 10:15
 * @version V1.0
 * @since 1.0
 **/
@Component
public class CustomImageCaptcha implements Captcha {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    private WordToImage wordToImage;

    @Autowired
    private WordGenerator wordGenerator;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${captcha.expireTime:50}")
    private long validateCodeExpire;

    @Value("${captcha.wordSize:5}")
    private int wordSize;

    private static final String VALIDATE_CODE_CACHE_KEY = "validateCode:web:";

    @Override
    public void render() throws IOException {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            String captchaId = httpServletRequest.getSession().getId();
            String code = wordGenerator.getWord(wordSize);
            BufferedImage challenge = wordToImage.getImage(code);
            //缓存redis key
            stringRedisTemplate.opsForValue().set(VALIDATE_CODE_CACHE_KEY + captchaId, code, validateCodeExpire, TimeUnit.SECONDS);
            JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);
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
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @Override
    public boolean validate(String code) {
        String captchaId = httpServletRequest.getSession().getId();
        //验证 captchaId 超时时间
        if (!stringRedisTemplate.hasKey(VALIDATE_CODE_CACHE_KEY + captchaId)) {
            throw new ValidateCodeExpireException("验证码过期");
        }
        String cachedCode = stringRedisTemplate.opsForValue().get(VALIDATE_CODE_CACHE_KEY + captchaId);
        boolean match = cachedCode.equalsIgnoreCase(code);
        stringRedisTemplate.delete(VALIDATE_CODE_CACHE_KEY + captchaId);
        return match;
    }
}
