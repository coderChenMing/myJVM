package com.learn.init;

/**
 * Project: jvm-learn
 * File Created at 2022-01-24 16:19:16:19
 * {@link}
 * 初始化阶段
 * @author <a href="mailto:charmFlightChen@gmail.com">chenming</a>
 * @version 1.0.0
 * @Type DemoA.java
 * @Desc
 * @date 2022/1/24 16:19
 */
public class DemoA {
    static int a = 0;
    static {
        a=1;
        b=1;
    }
    static {
        // static 静态代码块只能访问定义在代码块之前的变量 ,所以以下会编译错误
       // b = b + 1;
    }
    static int b = 0;

    public static void main(String[] args) {
        // 结果 1,0
        // static 静态代码块只能访问定义在代码块之前的变量
        System.out.println(a);
        System.out.println(b);
    }
}
