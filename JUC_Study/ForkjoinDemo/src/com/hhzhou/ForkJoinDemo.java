package com.hhzhou;
import java.util.concurrent.RecursiveTask;

/**
 * 求和计算的任务！
 * 3000 6000（ForkJoin） 9000（Stream并行流）
 * // 如何使用 forkjoin
 * // 1、forkjoinPool 通过它来执行
 * // 2、计算任务 forkjoinPool.execute(ForkJoinTask task)
 * // 3. 计算类要继承 ForkJoinTask
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private Long start;
    private Long end;

    private Long tmp = 10000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if ((end - start) < tmp){
            Long sum = 0L;
            for (long i = start; i <= end; i++){
                sum += i;
            }
            return sum;
        }else{ // forkjoin 递归
            Long middle = (start + end)/2;
            ForkJoinDemo forkJoinDemo = new ForkJoinDemo(start,middle);
            forkJoinDemo.fork();
            ForkJoinDemo forkJoinDemo1 = new ForkJoinDemo(middle,end);
            forkJoinDemo1.fork();

            return forkJoinDemo.join() + forkJoinDemo1.join();
        }
    }
}
