package com.learn.init;

/**
 * Project: jvm-learn
 * File Created at 2022-01-24 16:24:16:24
 * {@link}
 *
 * @author <a href="mailto:chenming1@eversec.cn">chenming</a>
 * @version 1.0.0
 * @Type ClassA.java
 * @Desc
 * @date 2022/1/24 16:24
 */
public class ClassA {
    static int a =2;
    int b;
    static {
        System.out.println("静态代码块:classA->1");
        System.out.println("classA 静态变量a="+a);
    }
    public ClassA(){
        System.out.println("classA无参实例化 classA->2");
        System.out.println("classA 成员变量b="+b);
    }

    public ClassA(int b) {
        this.b = b;
        System.out.println("classA带参实例化 classA->2");
        System.out.println("classA 成员变量b="+b);
    }
}
