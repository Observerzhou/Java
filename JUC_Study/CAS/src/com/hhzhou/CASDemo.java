package com.hhzhou;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:CAS
 * CAS:CompareAndSet  , 底层调用native方法 CompareAndSwap
 *  比较当前工作内存中的值与主存中的值是否一样，一样则执行操作，否则一致循环
 *  缺点：1.循环会耗时
 *        2.一次性只能能保证一个共享变量的原子性
 *        3.ABA问题
 *
 * @author: hhzhou
 * @create:2020/07/05
 */
public class CASDemo {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(2020);

        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(2020, 2022));
        System.out.println(atomicInteger.get());

        // 底层调用CompareAndSwap，自旋锁
        atomicInteger.getAndIncrement();
    }

}
