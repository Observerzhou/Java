package com.hhzhou;

import sun.security.jca.GetInstance;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.time.Instant;

/**
 * @description:懒汉式单例模式
 * 1. 普通单例---->单线程没问题，多线程时会有问题
 * 2. 加锁 synchronized，一次检测
 * 3. 加锁 synchronized，两次检测 ---->DCL懒汉式（双重检测锁）
 *    DCL懒汉式也可能会出现问题，new LazyInstance()可能发生指令重排操作
 * 4. 为避免指令重排，volatile
 * 5. DCL懒汉式也不是完全线程安全的，可以通过反射破坏
 *
 * 一般来说，双重检测锁就够用了
 *
 * @author: hhzhou
 * @create:2020/07/04
 */
public class LazyInstance {

    private static boolean flag = false;

    // 私有构造器
    private LazyInstance(){
//        System.out.println(Thread.currentThread().getName() + " OK");
//        synchronized (LazyInstance.class){
//            if (lazyInstance != null){
//                throw new RuntimeException("不要试图通过反射破坏单例！！！");
//            }
//        }

        synchronized (LazyInstance.class){
            if (flag == false){
                flag = true;
            }else{
                throw new RuntimeException("不要试图通过反射破坏单例！！！");
            }
        }


    }

    // 单例对象
    private static volatile LazyInstance lazyInstance;

    public static LazyInstance getInstance(){
        if (lazyInstance == null){
            synchronized(LazyInstance.class){
                if (lazyInstance == null){
                    lazyInstance = new LazyInstance();
                }
            }
        }
        return lazyInstance;
    }


    public static void main(String[] args) throws Exception {
//        LazyInstance lazyInstance1 = LazyInstance.getInstance();
        // 通过反射获取私有构造器
        Constructor<LazyInstance> declaredConstructor = LazyInstance.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyInstance lazyInstance2 = declaredConstructor.newInstance();

        Field flag = LazyInstance.class.getDeclaredField("flag");
        flag.set(lazyInstance2,false);

        LazyInstance lazyInstance1 = declaredConstructor.newInstance();

        // 结果发现，创建了两个不同的对象
        System.out.println(lazyInstance1);
        System.out.println(lazyInstance2);

    }
}
