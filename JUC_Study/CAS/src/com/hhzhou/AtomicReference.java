package com.hhzhou;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @description:原子引用，解决CAS的ABA问题
 * @author: hhzhou
 * @create:2020/07/05
 */
public class AtomicReference {

    public static void main(String[] args) {
        // 创建原子引用，引用对象为Integer类型
        // Integer等包装类判断相等时用等号
        AtomicStampedReference<Integer> integerAtomicStampedReference = new AtomicStampedReference<>(50, 1);
        int stamp = integerAtomicStampedReference.getStamp();

        // 线程A
        new Thread(()->{
            System.out.println("A1=>" + integerAtomicStampedReference.getStamp());

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 100 -> 200
            // compareAndSet底层判断相等用的 “=”，处理包装类时可能会出现问题
            integerAtomicStampedReference.compareAndSet(50,100,
                    integerAtomicStampedReference.getStamp(),integerAtomicStampedReference.getStamp()+1);
            System.out.println("A2=>"+integerAtomicStampedReference.getStamp());

            // 200 -> 100
            integerAtomicStampedReference.compareAndSet(100,50,
                    integerAtomicStampedReference.getStamp(),integerAtomicStampedReference.getStamp()+1);
            System.out.println("A3=>"+integerAtomicStampedReference.getStamp());

        },"A").start();


        // 线程B
        new Thread(()->{
            System.out.println("B1=>"+integerAtomicStampedReference.getStamp());

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            integerAtomicStampedReference.compareAndSet(50,51,
                    stamp,stamp+1);
            System.out.println("B2=>"+integerAtomicStampedReference.getStamp());
            System.out.println("=>"+integerAtomicStampedReference.getReference());
        },"B").start();
    }
}
