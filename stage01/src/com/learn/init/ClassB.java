package com.learn.init;

/**
 * Project: jvm-learn
 * File Created at 2022-01-24 16:26:16:26
 * {@link}
 *
 * @author <a href="mailto:charmFlightChen@gmail.com">chenming</a>
 * @version 1.0.0
 * @Type ClassB.java
 * @Desc
 * @date 2022/1/24 16:26
 */
public class ClassB extends ClassA{
    static int a =2;
    int b;
    static {
        System.out.println("静态代码块:ClassB->1");
        System.out.println("ClassB 静态变量a="+a);
    }
    public ClassB(){
        System.out.println("ClassB无参实例化 ClassB->2");
        System.out.println("ClassB 成员变量b="+b);
    }

    public ClassB(int b) {
        this.b = b;
        System.out.println("ClassB带参实例化 ClassB->2");
        System.out.println("ClassB 成员变量b="+b);
    }
}
