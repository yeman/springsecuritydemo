package com.yjt.springcloud.demo01.validatecode;

import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.engine.image.gimpy.DefaultGimpyEngine;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.AbstractManageableImageCaptchaService;

/**
 * @className DefaultCaptchaService
 * @description TODO
 * @author YM
 * @date 2021-05-24 11:10
 * @version V1.0
 * @since 1.0
 **/
public class DefaultCaptchaService extends AbstractManageableImageCaptchaService {

    public DefaultCaptchaService() {
        super(new FastHashMapCaptchaStore(), new DefaultGimpyEngine(), 180, 100000, 75000);
    }

    protected DefaultCaptchaService(CaptchaStore captchaStore, CaptchaEngine captchaEngine, int minGuarantedStorageDelayInSeconds, int maxCaptchaStoreSize, int captchaStoreLoadBeforeGarbageCollection) {
        super(captchaStore, captchaEngine, minGuarantedStorageDelayInSeconds, maxCaptchaStoreSize, captchaStoreLoadBeforeGarbageCollection);
    }

}
