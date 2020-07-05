package com.hhzhou;

import java.lang.reflect.Constructor;

/**
 * @description:枚举单例模式
 * @author: hhzhou
 * @create:2020/07/04
 */
public enum EnumInstance {

    INSTANCE;

    public EnumInstance getInstance(){
        return INSTANCE;
    }
}

class test{
    public static void main(String[] args) throws Exception {
//        EnumInstance enumInstance2 = EnumInstance.INSTANCE;
        Constructor<EnumInstance> declaredConstructor = EnumInstance.class.getDeclaredConstructor(String.class,int.class);
        declaredConstructor.setAccessible(true);
        EnumInstance enumInstance1 = declaredConstructor.newInstance();
        EnumInstance enumInstance2 = declaredConstructor.newInstance();

        System.out.println(enumInstance1);
        System.out.println(enumInstance2);
    }
}
