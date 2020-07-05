package com.hhzhou;

import java.util.concurrent.TimeUnit;

/**
 * @description:死锁
 * @author: hhzhou
 * @create:2020/07/05
 */
public class DeadlockDemo {

    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new MyThread(lockA,lockB),"T1").start();
        new Thread(new MyThread(lockB,lockA),"T2").start();

    }

}

class MyThread implements Runnable{

    private String lockA;
    private String lockB;

    public MyThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {

        // 获取lockA
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "having " + lockA + ", wait " + lockB);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 获取lockB
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "having " + lockB + ", wait " + lockA);
            }
        }
    }
}