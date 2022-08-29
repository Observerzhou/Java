package com.hhzhou;

import java.util.function.Consumer;

/**
 * @description:消费型接口
 * 有一个输入参数，无返回值
 *
 * @author: hhzhou
 * @create:2020/07/03
 */
public class ConsumerDemo  {

    public static void main(String[] args) {

//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String str) {
//                System.out.println(str);
//            }
//        };

        Consumer<String> consumer = (str)->{
            System.out.println(str);
        };

        consumer.accept("消费型接口");

    }
}
