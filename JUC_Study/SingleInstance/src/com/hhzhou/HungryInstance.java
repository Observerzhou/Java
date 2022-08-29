package com.hhzhou;

/**
 * @description:饿汉式单例模式
 * 饿汉式缺点：有可能会浪费资源：在类加载时创建对象，即使对象还没有使用
 *
 * @author: hhzhou
 * @create:2020/07/04
 */
public class HungryInstance {
    // 私有构造
    private HungryInstance(){

    }

    // 类加载创建对象-->饿汉
    private static HungryInstance hungryInstance = new HungryInstance();

    // 对外方法，获取单例对象
    public static HungryInstance getInstance(){
        return hungryInstance;
    }
}
