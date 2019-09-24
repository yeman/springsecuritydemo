package com.yjt.springcloud.demo01;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.KeyType;
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
        String publicKeySec = "datong123测试";
        String privateKeySec = "yjt";
        String encodePublickKeyStr = HexUtil.encodeHexStr(publicKeySec.getBytes(Charset.forName("UTF-8")),false);
        System.out.println("base64 public key:"+encodePublickKeyStr);
        PublicKey publicKey = KeyUtil.generateRSAPublicKey(encodePublickKeyStr.getBytes());
        PrivateKey privateKey = KeyUtil.generateRSAPrivateKey(privateKeySec.getBytes());
        String encodeStr = SecureUtil.rsa(privateKey.getEncoded(),publicKey.getEncoded()).encryptBase64(data, KeyType.PublicKey);
        System.out.println("encodeStr:"+encodeStr);
    }
}
