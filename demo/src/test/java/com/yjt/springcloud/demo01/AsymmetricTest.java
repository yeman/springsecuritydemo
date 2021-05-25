package com.yjt.springcloud.demo01;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.junit.Test;
import sun.security.util.DerEncoder;
import sun.security.x509.X509CRLImpl;

import javax.crypto.SecretKey;
import java.nio.charset.Charset;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CRLException;
import java.security.spec.X509EncodedKeySpec;

/**
 * @ClassName AsymmetricTest
 * @Description 非对称加密解密
 * @Author: YM
 * @Version V1.0
 * @Since V1.0
 * @Date: 2019-09-24 10:09
 **/
public class AsymmetricTest {

    // RSA 加密
    @Test
    public void testRsaEncode() throws CRLException {
        String data = "小时不识月,呼作白玉盘";
        RSA rsa = SecureUtil.rsa();
        PublicKey publicKey = rsa.getPublicKey();
        System.out.println("公钥key:"+Base64.encode(publicKey.getEncoded()));
        PrivateKey privateKey = rsa.getPrivateKey();
        System.out.println("私钥key:"+Base64.encode(privateKey.getEncoded()));
        //公钥加密
        byte[] encryptStr = rsa.encrypt(data.getBytes(),KeyType.PublicKey);
        System.out.println("加密串:"+ Base64.encode(encryptStr));

        //私钥匙解密
        String decryptStr =  rsa.decryptStr(Base64.encode(encryptStr),KeyType.PrivateKey);
        System.out.println("解密串:"+decryptStr);
    }

    //公钥key:MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCR4t8SV+VFlEEWeOFxLk73Dw/1frm178QTGFZ/xOyCqXgdeQe0sDPVfs4QSiyjehplFAeuGuMl16qwUQ/kcCIcACCASJzwsqvdsdM0WOSB/xf7a1rgzcO/0x6HowrcPm5/mGNaswiOZLwCG6/PPYDE+yft3pawY0yWCXMvSnZtUQIDAQAB
    //私钥key:MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJHi3xJX5UWUQRZ44XEuTvcPD/V+ubXvxBMYVn/E7IKpeB15B7SwM9V+zhBKLKN6GmUUB64a4yXXqrBRD+RwIhwAIIBInPCyq92x0zRY5IH/F/trWuDNw7/THoejCtw+bn+YY1qzCI5kvAIbr889gMT7J+3elrBjTJYJcy9Kdm1RAgMBAAECgYAcJClnvnDJpTmQ/ixuysIMwmkPsCxeviuSi2VULEZ7RfmXpdsorbyx+E4Dsms99bXVvbFlkmYyzJFuhZSNOlw5w+jPB3cF2MY3hwffzBwyqoedyJ4Hwwo2TUMCC3WxZvc/WD8PeozJaK/Il4diG+rbBt3F9OEzsLAs7xsFXYo6yQJBAMh90Dkuo3t85F7NXCc6TjhGBU/kK0rKnLK2O6mna+XDWaPdqRgK895x9O+eTWiUrGMzrD/Wa/NNXpV/Z6NqfV8CQQC6RtCd3jeC/4B83xG+pSofeAEzFDSeg3+O3VsH2irLbqKVa5OvEr7Sup2HHNq+Q2UQJEXHRJwKgcFpBKioomNPAkEAqkEU4oo1b/cl40+W0wUTSXxEpIxJKzEVkjloPvCakrmqVJo2LuGWKC8zZYxThdzC61cFDgzjDPCFwoQ3mTEkFwJAK4ndvOhxpSa/C3DAHwVPwSc1cJDqc5pcuDG40y8FDaOAUNhiHmuNOofvxeLSpeHuv0UbIiZBKpuFb1xtZ2z9NQJAENZLjnKjGUv+BFwwIkHtQ2H4HF00W5xSLuMp48JnQl+Ct//IWKKNDWYNZOvNdtjqrPR3MH9EvdzCmjFzeU2aXw==
    @Test
    public void testRsa(){
        //Base64加密后的
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCR4t8SV+VFlEEWeOFxLk73Dw/1frm178QTGFZ/xOyCqXgdeQe0sDPVfs4QSiyjehplFAeuGuMl16qwUQ/kcCIcACCASJzwsqvdsdM0WOSB/xf7a1rgzcO/0x6HowrcPm5/mGNaswiOZLwCG6/PPYDE+yft3pawY0yWCXMvSnZtUQIDAQAB";
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJHi3xJX5UWUQRZ44XEuTvcPD/V+ubXvxBMYVn/E7IKpeB15B7SwM9V+zhBKLKN6GmUUB64a4yXXqrBRD+RwIhwAIIBInPCyq92x0zRY5IH/F/trWuDNw7/THoejCtw+bn+YY1qzCI5kvAIbr889gMT7J+3elrBjTJYJcy9Kdm1RAgMBAAECgYAcJClnvnDJpTmQ/ixuysIMwmkPsCxeviuSi2VULEZ7RfmXpdsorbyx+E4Dsms99bXVvbFlkmYyzJFuhZSNOlw5w+jPB3cF2MY3hwffzBwyqoedyJ4Hwwo2TUMCC3WxZvc/WD8PeozJaK/Il4diG+rbBt3F9OEzsLAs7xsFXYo6yQJBAMh90Dkuo3t85F7NXCc6TjhGBU/kK0rKnLK2O6mna+XDWaPdqRgK895x9O+eTWiUrGMzrD/Wa/NNXpV/Z6NqfV8CQQC6RtCd3jeC/4B83xG+pSofeAEzFDSeg3+O3VsH2irLbqKVa5OvEr7Sup2HHNq+Q2UQJEXHRJwKgcFpBKioomNPAkEAqkEU4oo1b/cl40+W0wUTSXxEpIxJKzEVkjloPvCakrmqVJo2LuGWKC8zZYxThdzC61cFDgzjDPCFwoQ3mTEkFwJAK4ndvOhxpSa/C3DAHwVPwSc1cJDqc5pcuDG40y8FDaOAUNhiHmuNOofvxeLSpeHuv0UbIiZBKpuFb1xtZ2z9NQJAENZLjnKjGUv+BFwwIkHtQ2H4HF00W5xSLuMp48JnQl+Ct//IWKKNDWYNZOvNdtjqrPR3MH9EvdzCmjFzeU2aXw==";
        String data = "小时不识月,呼作白玉盘";
        RSA rsa = SecureUtil.rsa(null,publicKey);
        byte[] encryptByte = rsa.encrypt(data.getBytes(),KeyType.PublicKey);
        String encryptStr = Base64.encode(encryptByte);
        System.out.println("通过公钥加密后的数据Base64加密串:"+encryptStr);

        RSA clientRsa = SecureUtil.rsa(privateKey,null);
        Long start = System.currentTimeMillis();
        String  decryptStr = clientRsa.decryptStr(encryptStr,KeyType.PrivateKey);
        Long end = System.currentTimeMillis();
        System.out.println("RSA私钥解密耗时"+(end-start)+" ms");
        String  decryptStr2 = new String(clientRsa.decrypt(encryptByte,KeyType.PrivateKey));
        System.out.println("通过私钥解密后的解密串:"+decryptStr);
        System.out.println("通过私钥解密后的解密串:"+decryptStr2);


    }
}
