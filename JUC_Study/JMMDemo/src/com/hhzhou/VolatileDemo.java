package com.hhzhou;

import com.sun.deploy.util.SyncAccess;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:volatile不保证原子性
 * @author: hhzhou
 * @create:2020/07/04
 */
public class VolatileDemo {

    // volatile不保证原子性
//    private static volatile int num = 0;

    // 解决方法二：使用JUC 的Automic包,效率比lock和synchronized要高很多
    private static volatile AtomicInteger num = new AtomicInteger();

    // 解决方法一：lock锁、synchronized可以保证原子性
//    private static synchronized void add(){
////        num++;
////    }
//    private static Lock lock = new ReentrantLock();
//    private static void add(){
//        lock.lock();
//        try {
//            // 业务代码
//            num++;
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            lock.unlock();
//        }
//    }

    private static void add(){
        num.getAndIncrement();
    }

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            new Thread( ()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            } ).start();
        }

        // 判断子线程执行结束
        while (Thread.activeCount()>2){ // 默认两个线程：主线程，GC线程
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + num);
    }

}
