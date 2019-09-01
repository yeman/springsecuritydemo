package com.yjt.springcloud.demodb.web;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * json模版
 * ClassName: JsonTemplate
 * Date: 2019-08-31 21:56
 * author Administrator
 * version V1.0
 */
@Data
@Builder
@Accessors(chain = true)
public class JsonTemplate {

    /**
     * 成功
     */
    private static  final String SUCCESS = "1";
    /**
     * 失败
     */
    private static  final String FAILED = "0";

    /**
     * 状态
     */
    private String status;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    /**
     * 附加
     */
    private Object options;

    /**
     * 错误堆栈信息
     */
    private Object error;


    public static JsonTemplate success(Object data){
       return JsonTemplate.builder().status(JsonTemplate.SUCCESS).message("操作成功").data(data).build();
    }

    public static JsonTemplate success(Object data,String message){
       return JsonTemplate.builder().status(JsonTemplate.SUCCESS).message(message).data(data).build();
    }
     public static JsonTemplate success(Object data,String message,Object options){
           return JsonTemplate.builder().status(JsonTemplate.SUCCESS).message(message).data(data).options(options).build();
     }

    public static JsonTemplate failed(){
        return JsonTemplate.builder().status(JsonTemplate.FAILED).message("操作失败").build();
    }

     public static JsonTemplate failed(String message){
           return JsonTemplate.builder().status(JsonTemplate.FAILED).message(message).build();
     }

     public static JsonTemplate failed(String message,Object error){
           return JsonTemplate.builder().status(JsonTemplate.FAILED).message(message).error(error).build();
     }


}
