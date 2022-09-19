package com.example.template.common.response;

import com.alibaba.fastjson.JSONObject;
import com.example.template.common.exception.BaseErrorInfoInterface;
import com.example.template.common.exception.ExceptionEnum;

/**
 * @description: 自定义数据传输
 * @author: zhouhuihui
 * @date: 2022/9/8
 */
public class CommonResponse extends Response {

    public CommonResponse() {
    }

    public CommonResponse(BaseErrorInfoInterface errorInfo) {
        this.code = errorInfo.getResultCode();
        this.message = errorInfo.getResultMsg();
    }

    /**
     * 执行成功, 无返回参数
     * 
     * @return com.example.template.common.response.CommonResponse
     */
    public static CommonResponse success() {
        return success(null);
    }

    /**
     * 执行成功, 带返回参数
     * 
     * @param data 返回结果
     * @return com.example.template.common.response.CommonResponse
     */
    public static CommonResponse success(Object data) {
        CommonResponse comRes = new CommonResponse();
        comRes.setCode(ExceptionEnum.SUCCESS.getResultCode());
        comRes.setMessage(ExceptionEnum.SUCCESS.getResultMsg());
        comRes.setResult(data);
        return comRes;
    }

    /**
     * 执行失败
     *
     * @param errorInfo 错误信息
     * @return com.example.template.common.response.CommonResponse
     */
    public static CommonResponse error(BaseErrorInfoInterface errorInfo) {
        CommonResponse comRes = new CommonResponse();
        comRes.setCode(errorInfo.getResultCode());
        comRes.setMessage(errorInfo.getResultMsg());
        comRes.setResult(null);
        return comRes;
    }

    /**
     * 执行失败
     *
     * @param code 错误码
     * @param message 错误信息
     * @return com.example.template.common.response.CommonResponse
     */
    public static CommonResponse error(String code, String message) {
        CommonResponse comRes = new CommonResponse();
        comRes.setCode(code);
        comRes.setMessage(message);
        comRes.setResult(null);
        return comRes;
    }

    /**
     * 执行失败
     *
     * @param message 错误信息
     * @return com.example.template.common.response.CommonResponse
     */
    public static CommonResponse error(String message) {
        CommonResponse comRes = new CommonResponse();
        comRes.setCode("-1");
        comRes.setMessage(message);
        comRes.setResult(null);
        return comRes;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
