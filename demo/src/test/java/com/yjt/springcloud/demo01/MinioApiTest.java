package com.yjt.springcloud.demo01;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName MinioApiTest
 * @Description TODO
 * @Author: YM
 * @Version V1.0
 * @Since V1.0
 * @Date: 2019-09-24 14:51
 **/
public class MinioApiTest {

    private final String endPoint = "http://127.0.0.1:9000";
    private final String accessKey = "P5E9LNEFDIZ4YZ5QRWH7";
    private final String secretKey = "Z7T9x7XLjp+4iSmLZ4vMYX+k8PueJo+Xr3ihbLdu";
    private MinioClient minioClient = null;

    @Before
    public void init(){
        try {
            minioClient = new MinioClient(endPoint,accessKey,secretKey);
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        } catch (InvalidPortException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testUpload(){
       final String bulketName = "image";
        try {
            if(!minioClient.bucketExists(bulketName)){
                minioClient.makeBucket(bulketName);
            }
            //上传对象
            minioClient.putObject(bulketName,"程序员搞笑","C:\\Users\\Administrator\\Pictures\\搞笑.jpg");
            System.out.println("图片文件上传");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //生成签名过期时间的存储对象url 可用于分享
    @Test
    public void testSiginUrl(){
        final String bulketName = "image";
        Integer expire = 10;
        try {
            if(!minioClient.bucketExists(bulketName)){
                minioClient.makeBucket(bulketName);
            }
            //生成签名url访问地址过期时间
            String url = minioClient.presignedGetObject(bulketName,"程序员搞笑",expire);
            System.out.println("图片文件上传,地址:"+url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取对象url
    @Test
    public void getObjectUrl(){
        final String bulketName = "image";
        try {
            if(!minioClient.bucketExists(bulketName)){
                minioClient.makeBucket(bulketName);
            }
            //生成url访问地址过期时间
            String url = minioClient.getObjectUrl(bulketName,"程序员搞笑");
            System.out.println("图片文件上传,地址:"+url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDownLoad() throws IOException {
        final String bulketName = "image";
        FileOutputStream fileOutputStream = null;
        try {
            InputStream is = minioClient.getObject(bulketName,"程序员搞笑");
            //FileUtil.writeFromStream(is,new File("C:\\Users\\Administrator\\Pictures\\程序员搞笑.JPG"));
            File tempFile = new File("C:\\Users\\Administrator\\Pictures\\程序员搞笑.JPG");
            fileOutputStream = new FileOutputStream(tempFile);
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int len =0;
            while((len=is.read(buffer))!=-1){
                fileOutputStream.write(buffer,0, len);
            }
            fileOutputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fileOutputStream!=null){fileOutputStream.close();}
        }
    }
    @Test
    public void testDelete(){
        final String bulketName = "image";
        try {
            minioClient.removeObject(bulketName,"程序员搞笑");
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
