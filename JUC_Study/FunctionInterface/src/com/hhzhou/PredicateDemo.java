package com.hhzhou;

import java.util.function.Predicate;

/**
 * @description:断定型接口
 * 一个入参，返回值类型为 布尔值
 *
 * @author: hhzhou
 * @create:2020/07/03
 */
public class PredicateDemo {
    public static void main(String[] args) {

        // 匿名内部类
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String str) {
//                return str.isEmpty();
//            }
//        };

        // lambda表达式
        Predicate<String> predicate = (str)->{ return str.isEmpty(); };

        System.out.println(predicate.test("断定型接口测试"));
    }
}
