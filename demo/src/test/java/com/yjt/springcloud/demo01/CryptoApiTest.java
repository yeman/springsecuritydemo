package com.yjt.springcloud.demo01;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @ClassName CryptoApiTest
 * @Description 对称加密解密
 * @Author: YM
 * @Version V1.0
 * @Since V1.0
 * @Date: 2019-09-23 10:49
 **/
public class CryptoApiTest {

    @Test
    public void test01() {
        byte[] data = "中华人民共和国".getBytes();
        RSA rsa = SecureUtil.rsa();
        String publicKeyBase64 = rsa.getPublicKeyBase64();
        String privateKeyBase64 = rsa.getPrivateKeyBase64();
        System.out.println("public key:" + publicKeyBase64);
        System.out.println("private key:" + privateKeyBase64);
        byte[] encrypt = rsa.encrypt(data, KeyType.PublicKey);
       //  System.out.println("密文:"+StrUtil.str(encrypt,CharsetUtil.CHARSET_UTF_8));
        String decodeStr = StrUtil.str(rsa.decrypt(encrypt, KeyType.PrivateKey), CharsetUtil.CHARSET_UTF_8);
        System.out.println("解密:" + decodeStr);
    }

    // aes 对称加密解密
    @Test
    public void testAes() throws NoSuchAlgorithmException {
        String data = "中华人民共和国";
        //hex bba50ed97f40e2748cd8b0d081607de089226b2354db6ac9ed7fbedb5ce154ca
        //base64 qeC+xe2HiLm5F0Tei3n93VQaGvSttql3EBnQX5Oy+xQ=

        /*自定义明文加密key
        String publicKeyStr ="wuhan";
        KeyGenerator keyGenerator = KeyGenerator.getInstance(SymmetricAlgorithm.AES.getValue());
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(publicKeyStr.getBytes());
        keyGenerator.init(128,secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] byteKey = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(),secretKey.getEncoded()).getEncoded();
        */

        // 密钥要求 Key length not 128/192/256 bits.
        byte[] byteKey = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        System.out.println("密钥:"+ Base64.encode(byteKey));
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, byteKey);
        String encodeStr = new String(aes.encryptHex(data));
        System.out.println("hex加密:" + encodeStr);
        String decodeStr = aes.decryptStr(encodeStr);
        System.out.println("hex解密:" + decodeStr);
        // base64对称
        String base64EncodeStr = new String(aes.encryptBase64(data));
        System.out.println("base64加密:" + base64EncodeStr);
        System.out.println("base64解密:" + aes.decryptStr(base64EncodeStr));
        ;
    }

    //Des加密解密
    @Test
    public void testDes() {
        String data = "中华人民共和国666";
        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded();
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.DES, key);
        String encodeStr = new String(aes.encryptHex(data));
        System.out.println("hex加密:" + encodeStr);
        String decodeStr = aes.decryptStr(encodeStr);
        System.out.println("hex解密:" + decodeStr);
        // base64对称
        String base64EncodeStr = new String(aes.encryptBase64(data));
        System.out.println("base64加密:" + base64EncodeStr);
        System.out.println("base64解密:" + aes.decryptStr(base64EncodeStr));
        ;
    }

    //RC2
    @Test
    public void testRC2() {
        String data = "中华人民共和国666";
        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.RC2.getValue()).getEncoded();
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.RC2, key);
        String encodeStr = new String(aes.encryptHex(data));
        System.out.println("hex加密:" + encodeStr);
        String decodeStr = aes.decryptStr(encodeStr);
        System.out.println("hex解密:" + decodeStr);
        // base64对称
        String base64EncodeStr = new String(aes.encryptBase64(data));
        System.out.println("base64加密:" + base64EncodeStr);
        System.out.println("base64解密:" + aes.decryptStr(base64EncodeStr));
        ;
    }

    //Blowfish对称加密解密
    @Test
    public void testBlowfish() {
        String data = "中华人民共和国666";
        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.Blowfish.getValue()).getEncoded();
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.Blowfish, key);
        String encodeStr = new String(aes.encryptHex(data));
        System.out.println("hex加密:" + encodeStr);
        String decodeStr = aes.decryptStr(encodeStr);
        System.out.println("hex解密:" + decodeStr);
        // base64对称
        String base64EncodeStr = new String(aes.encryptBase64(data));
        System.out.println("base64加密:" + base64EncodeStr);
        System.out.println("base64解密:" + aes.decryptStr(base64EncodeStr));
        ;
    }

    @Test
    public void testBPE() {
        String data = "中华人民共和国666";
        //hex e35218df2a9d07af77a583b99d598bbce914449630b81650cd7f5bebe21be37e
        //base64 41IY3yqdB693pYO5nVmLvOkURJYwuBZQzX9b6+Ib434=
        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        System.out.println("加密key:"+ Base64.encode(key));
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.PBEWithMD5AndDES, key);
        String encodeStr = new String(aes.encryptHex(data));
        System.out.println("hex加密:" + encodeStr);
        String decodeStr = aes.decryptStr(encodeStr);
        System.out.println("hex解密:" + decodeStr);
        // base64对称
        String base64EncodeStr = new String(aes.encryptBase64(data));
        System.out.println("base64加密:" + base64EncodeStr);
        System.out.println("base64解密:" + aes.decryptStr(base64EncodeStr));
        ;
    }


}
