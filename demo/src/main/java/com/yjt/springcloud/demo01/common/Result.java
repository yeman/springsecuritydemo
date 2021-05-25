package com.yjt.springcloud.demo01.common;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @className Result
 * @description 返回结果
 * @author YM
 * @date 2021-05-24 17:37
 * @version V1.0
 * @since 1.0
 **/
@Accessors(chain = true)
@Builder
@Data
public final class Result {

    /**
     * 成功消息
     **/
    private static final String SUCCESS_MSG = "成功";

    /**
     * 失败消息
     **/
    private static final String FAILURE_MSG = "失败";

    /**
     * 成功状态
     **/
    private static final String SUCCESS_STATUS = "1";

    /**
     * 失败状态
     **/
    private static final String FAILURE_STATUS = "0";

    /**
     * 交易id
     **/
    private Long txnId;

    /**
     * 状态 0 失败,1 正常,2 会话异常
     **/
    private String status;

    /**
     * 数据
     **/
    private Object data;

    /**
     * 消息
     **/
    private String msg;

    /**
     * 堆栈消息
     **/
    private String stackMsg;

    /**
     * @description 成功 默认消息
     * @author YM
     * @date 2021/5/24 18:12
     * @param data
     * @return com.yjt.springcloud.demo01.common.Result
     */
    public static Result success(Object data) {
        return success(data, SUCCESS_MSG);
    }

    /**
     * @description 带自定义消息成功
     * @author YM
     * @date 2021/5/24 18:12
     * @param data
     * @param msg
     * @return com.yjt.springcloud.demo01.common.Result
     */
    public static Result success(Object data, String msg) {
        return success(data, msg, null);
    }

    /**
     * @description 带自定义消息成功,交易流水
     * @author YM
     * @date 2021/5/24 18:13
     * @param data
     * @param msg
     * @param txnId
     * @return com.yjt.springcloud.demo01.common.Result
     */
    public static Result success(Object data, String msg, Long txnId) {
        return Result.builder().status(SUCCESS_STATUS).data(data).msg(msg).txnId(txnId).build();
    }

    /**
     * @description 失败消息
     * @author YM
     * @date 2021/5/25 9:52
     * @param msg
     * @return com.yjt.springcloud.demo01.common.Result
     */
    public static Result failed(String msg) {
        return failed(null,msg);
    }

    /**
     * @description 失败,默认消息
     * @author YM
     * @date 2021/5/24 18:14
     * @param data
     * @return com.yjt.springcloud.demo01.common.Result
     */
    public static Result failed(Object data) {
        return failed(data, FAILURE_STATUS);
    }
    /**
     * @description 失败,自定义消息
     * @author YM
     * @date 2021/5/24 18:14
     * @param data
     * @param msg
     * @return com.yjt.springcloud.demo01.common.Result
     */
    public static Result failed(Object data, String msg) {
        return failed(data, msg, null);
    }

    /**
     * @description 失败,失败消息,错误信息
     * @author YM
     * @date 2021/5/25 9:02
     * @param data
     * @param msg
     * @param error
     * @return com.yjt.springcloud.demo01.common.Result
     */
    public static Result failed(Object data, String msg, String error) {
        return Result.builder().status(FAILURE_STATUS).data(data).msg(msg).stackMsg(error).build();
    }
}
