package com.learn.method.area;

/**
 * Project: jvm-learn
 * File Created at 2022-01-24 15:40:15:40
 * {@link}
 * 类变量有两次赋初始值的过程，一次在准备阶段，赋予初始值（也可以是指定值）；另外一次在初始化阶段，赋予程序员定义的值。
 * @author <a href="mailto:charmFlightChen@gmail.com">chenming</a>
 * @version 1.0.0
 * @Type DemoB.java
 * @Desc
 * @date 2022/1/24 15:40
 */
public class DemoB {
    public static void main(String[] args) {
        int a;
        //以下代码会编译错误,错误原因:局部变量没有连接:验证->准备->解析 中的准备阶段,不会赋值初始值
        // 而类变量会赋值两次:一次在准备阶段,一次在初始化
        //System.out.println(a);
        char[]  v1= {1,2};
    }
}
