package com.example.template.controller;

import com.example.template.common.response.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试controller
 * @author: zhouhh19
 * @date: 2022/9/8
 **/
@RestController
public class TestController {

    @GetMapping("/api/test")
    public CommonResponse test() {
        return CommonResponse.success();
    }

}
