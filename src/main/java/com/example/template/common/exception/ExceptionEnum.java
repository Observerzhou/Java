package com.example.template.common.exception;

/**
 * @description: 异常处理枚举类
 * @author: zhouhuihui
 * @date: 2021/9/8
 */
public enum ExceptionEnum implements BaseErrorInfoInterface{

    SUCCESS("200", "执行成功!"),
    BODY_NOT_MATCH("400","请求的数据格式不符!"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503","服务器正忙，请稍后再试!");

    /**
     * 错误码
     */
    private final String resultCode;

    /**
     * 错误描述
     */
    private final String resultMsg;

    ExceptionEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
