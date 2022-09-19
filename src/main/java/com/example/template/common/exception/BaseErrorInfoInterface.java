package com.example.template.common.exception;

/**
 * @description: 异常服务接口
 * @author: zhouhuihui
 * @date: 2022/9/8
 */
public interface BaseErrorInfoInterface {

    /**
     * 错误码
     *
     * @return java.lang.String
     */
    String getResultCode();

    /**
     * 错误描述
     *
     * @return java.lang.String
     */
    String getResultMsg();
}
