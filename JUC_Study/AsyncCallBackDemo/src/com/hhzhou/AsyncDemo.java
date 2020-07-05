package com.hhzhou;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @description:异步回调
 * @author: hhzhou
 * @create:2020/07/04
 */
public class AsyncDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        // 无返回值的异步回调
//        CompletableFuture<Void> completableFuture =  CompletableFuture.runAsync(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + "runAsync=>Void");
//        });
//
//        System.out.println("1111");
//
//        completableFuture.get(); //获取阻塞执行结果


        // 有返回值的异步回调
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"supplyAsync=>Integer");
//            int a = 10/0;
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1024;
        });

        System.out.println("1111");

        System.out.println(completableFuture1.whenComplete((t, u) -> {
            // t，正常的返回结果
            System.out.println("t==>" + t);
            // u，错误（异常信息）
            System.out.println("u==>" + u);
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 404;
        }).get());



    }
}
