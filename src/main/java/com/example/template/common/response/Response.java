package com.example.template.common.response;

import lombok.Data;

/**
 * @description: 返回类型
 * @author: zhouhh19
 * @date: 2022/9/8
 **/
@Data
public class Response {
    /**
     * 响应代码
     */
    protected String code;

    /**
     * 响应消息
     */
    protected String message;

    /**
     * 响应结果
     */
    protected Object result;
}
