package com.hhzhou;

import java.util.function.Function;

/**
 * @description:函数型接口
 * 两个参数：一个输入参数，一个返回值类型参数
 *
 * @author: hhzhou
 * @create:2020/07/03
 */
public class FunctionDemo {

    public static void main(String[] args) {

//        Function<String, String> function = new Function<String, String>() {
////            @Override
////            public String apply(String str) {
////                return str;
////            }
////        };

        Function<String, String> function = (str)->{ return str; };

        System.out.println(function.apply("函数型接口测试"));
    }
}
