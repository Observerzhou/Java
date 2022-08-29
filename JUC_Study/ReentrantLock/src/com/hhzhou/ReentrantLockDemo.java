package com.hhzhou;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:可重入锁 递归锁
 * @author: hhzhou
 * @create:2020/07/05
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(()->{
            try {
                phone.sms();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            phone.call();
        },"B").start();
    }
}

class Phone{
//    public synchronized void sms() throws InterruptedException {
//        System.out.println(Thread.currentThread().getName() + " sms");
//        TimeUnit.SECONDS.sleep(2);
//
//        call();
//    }
//
//    public synchronized void call(){
//        System.out.println(Thread.currentThread().getName() + " call");
//    }

    Lock lock = new ReentrantLock();

    public  void sms() throws InterruptedException {
        // 加锁与解锁要对应匹配
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " sms");
            TimeUnit.SECONDS.sleep(2);

            call();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
            lock.unlock();
        }

    }

    public  void call(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " call");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
