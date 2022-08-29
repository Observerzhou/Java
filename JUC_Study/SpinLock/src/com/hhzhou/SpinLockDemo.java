package com.hhzhou;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:自旋锁
 * 自定义自旋锁
 *
 * @author: hhzhou
 * @create:2020/07/05
 */
public class SpinLockDemo {

    // 创建一个原子引用---CAS---引用一个线程对象
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "==>myLock");

        // 自选锁
        while(!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnlock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "==>myUnlock");

        atomicReference.compareAndSet(thread,null);
    }
}

class test{
    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLockDemo.myUnlock();
            }
        }, "A").start();


        // 保证A线程先拿到锁
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLockDemo.myUnlock();
            }
        },"B").start();
    }
}
