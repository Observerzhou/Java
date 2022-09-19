package com.example.template.common.exception;

import com.example.template.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 自定义异常处理, 可新增异常处理
 *
 * @author: zhouhuihui
 * @date: 2022/9/8
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     * 
     * @param req 请求
     * @param e 异常
     * @return com.example.template.common.response.CommonResponse
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public CommonResponse bizExceptionHandler(HttpServletRequest req, BizException e){
        log.error("发生业务异常！原因：{}", e.getErrorMsg());
        return CommonResponse.error(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     *
     * @param req 请求
     * @param e 异常
     * @return com.example.template.common.response.CommonResponse
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public CommonResponse exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因:", e);
        return CommonResponse.error(ExceptionEnum.BODY_NOT_MATCH);
    }

    /**
     * 处理其他异常
     *
     * @param req 请求
     * @param e 异常
     * @return com.example.template.common.response.CommonResponse
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResponse exceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常！原因:", e);
        return CommonResponse.error(ExceptionEnum.INTERNAL_SERVER_ERROR);
    }
}
