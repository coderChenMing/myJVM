package com.learn.class_load;

/**
 * Project: jvm-learn
 * File Created at 2022-01-24 17:57:17:57
 * {@link}
 *
 * @author <a href="mailto:charmFlightChen@gmail.com">chenming</a>
 * @version 1.0.0
 * @Type ClassA.java
 * @Desc
 * @date 2022/1/24 17:57
 */
public class ClassA {
    private ClassB classB = new ClassB();

    public static void main(String[] args) {
        ClassA classA = new ClassA();
        long num = 4321;
        long ret = classA.classB.test(num);
        System.out.println(ret);

    }

}
