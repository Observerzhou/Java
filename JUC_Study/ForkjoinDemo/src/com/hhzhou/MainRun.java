package com.hhzhou;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @description:测试
 * @author: hhzhou
 * @create:2020/07/04
 */
public class MainRun {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. 普通求和
//        test1();

        // 2. 使用ForkJoin求和
//        test2();

        // 3.使用并行流计算
        tets3();
    }

    // 1. 普通求和
    public static void test1(){
        Long sum = 0L;

        long startTime = System.currentTimeMillis();
        for (Long i = 0L; i <= 10_0000_0000L; i++){
            sum += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("sum=" + sum + "-------" + (endTime-startTime)/1000);
    }

    // 2.forkjoin
    //  使用ForkJoinPool执行ForkJoin操作
    public static void test2() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinDemo = new ForkJoinDemo(0L,10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoinDemo);
        Long sum = submit.get();

        long endTime = System.currentTimeMillis();
        System.out.println("sum=" + sum + "-------------" + (endTime-startTime)/1000);

    }

    // 3.使用并行流计算
    public static void tets3(){
        long startTime = System.currentTimeMillis();
        Long sum = LongStream.rangeClosed(0L,10_0000_0000L).parallel().reduce(0,Long::sum);
        long endTime = System.currentTimeMillis();
        System.out.println("sum="+sum + "----------" + (endTime-startTime));
    }

}
