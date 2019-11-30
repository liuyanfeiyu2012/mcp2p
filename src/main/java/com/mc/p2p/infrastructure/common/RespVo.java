package com.mc.p2p.infrastructure.common;

/**
 * @author: 谢星星
 * @date: 2019/11/30 20:35
 * @Description:
 */
public class RespVo<T> {

    public static final String SUCCESS_CODE = "0000";

    public static final String SUCCESS_MSG = "操作成功";

    public static final String FAIL_CODE = "0911";

    public static final String FAIL_MSG = "系统正忙，请稍后重试";

    public static final String REQUEST_PARAM_ERROR_CODE = "0002";

    public static final String REQUEST_PARAM_ERROR_MSG = "注意：${}";

    public static final String NOT_LOGIN_CODE = "0003";

    public static final String NOT_LOGIN_MSG = "请先登录";

    public static final String TOO_FREQUENCY_CODE = "0004";

    public static final String TOO_FREQUENCY_MSG = "您的操作太快了";


    private String resCode;

    private String resMsg;

    private T result;

    public RespVo() {
    }

    public RespVo(String resCode, String resMsg, T result) {
        this.resCode = resCode;
        this.resMsg = resMsg;
        this.result = result;
    }

    public static RespVo SUCCESS() {
        return CUSTOM(SUCCESS_CODE, SUCCESS_MSG, null);
    }

    public static <T> RespVo<T> SUCCESS(T obj) {
        return CUSTOM(SUCCESS_CODE, SUCCESS_MSG, obj);
    }

    public static RespVo FAIL() {
        return CUSTOM(FAIL_CODE, FAIL_MSG, null);
    }

    public static RespVo REQUEST_PARAM_ERROR() {
        return CUSTOM(REQUEST_PARAM_ERROR_CODE, REQUEST_PARAM_ERROR_MSG, null);
    }

    public static RespVo NOT_LOGIN() {
        return CUSTOM(NOT_LOGIN_CODE, NOT_LOGIN_MSG, null);
    }

    public static RespVo TOO_FREQUENCY() {
        return CUSTOM(TOO_FREQUENCY_CODE, TOO_FREQUENCY_MSG, null);
    }

    public static <T> RespVo<T> CUSTOM(String resCode, String resMsg, T result) {
        return new RespVo(resCode, resMsg, result);
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
