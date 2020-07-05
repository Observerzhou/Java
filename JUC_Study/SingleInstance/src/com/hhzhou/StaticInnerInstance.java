package com.hhzhou;

import java.sql.Statement;

/**
 * @description:静态内部类实现单例模式
 * @author: hhzhou
 * @create:2020/07/04
 */
public class StaticInnerInstance {

    private StaticInnerInstance(){

    }

    public static StaticInnerInstance getInstance(){
        return InnerClass.staticInnerInstance;
    }

    public static class InnerClass{
        private static final StaticInnerInstance staticInnerInstance = new StaticInnerInstance();
    }

}
