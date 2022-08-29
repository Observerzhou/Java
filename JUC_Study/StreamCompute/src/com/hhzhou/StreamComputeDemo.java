package com.hhzhou;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:流式计算
 *
 * 题目要求：一分钟内完成此题，只能用一行代码实现！
 * 现在有5个用户！筛选：
 * 1、ID 必须是偶数
 * 2、年龄必须大于23岁
 * 3、用户名转为大写字母
 * 4、用户名字母倒着排序
 * 5、只输出一个用户！
 *
 * @author: hhzhou
 * @create:2020/07/03
 */
public class StreamComputeDemo {

    public static void main(String[] args) {
        User u1 = new User(1,"a",21);
        User u2 = new User(2,"b",22);
        User u3 = new User(3,"c",23);
        User u4 = new User(4,"d",24);
        User u5 = new User(6,"e",25);

        List<User> list = Arrays.asList(u1,u2,u3,u4,u5);


        // lambda表达式 + 函数式接口 + 链式编程 + 流式计算
        // 所有的函数式接口都可以用lambda表达式
        list.stream()
                .filter(user->user.getId()%2 == 0)
                .filter(user->user.getAge()>22)
                .map(user->user.getName().toUpperCase())
                .sorted( (name1,name2)-> name2.compareTo(name1) )
//                .sorted()
                .limit(1)
                .forEach(System.out::println);
//                .forEach((user)->{ System.out.println(user.getName());});

    }

}
