package com.hhzhou;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @description:供给型接口
 *  没有输入参数，只有返回值
 *
 * @author: hhzhou
 * @create:2020/07/03
 */
public class SupplierDemo {

    public static void main(String[] args) {

//        Supplier<String> supplier = new Supplier<String>() {
//            @Override
//            public String get() {
//                return "供给型接口";
//            }
//        };

        Supplier<String> supplier = ()->{ return "供给型接口"; };

        System.out.println(supplier.get());

    }
}
