package com.hhzhou;

import java.util.concurrent.TimeUnit;

/**
 * @description:java内存模型（可能出现的问题）--->volatile保证可见性
 * @author: hhzhou
 * @create:2020/07/04
 */
public class JMMDemo {

    // volatile可以保证主存中的共享变量的可见性（对各线程来说）
    private static volatile int num = 0;

    public static void main(String[] args) {

        // 线程中对主存中num的变化是不感知的，所以线程一直死循环
        new Thread(()->{
            while (num == 0){

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 主线程修改num的值，更新到主存中，主存中num值为1
        num = 1;
        System.out.println(num);
    }
}
