package com.learn.init;

/**
 * Project: jvm-learn
 * File Created at 2022-01-24 16:27:16:27
 * {@link}
 *  其中 static 字段和 static 代码块，是属于类的，在类的加载的初始化阶段就已经被执行。
 *  类信息会被存放在方法区，在同一个类加载器下，这些信息有一份就够了，所以上面的 static 代码块只会执行一次，它对应的是 <clinit> 方法。
 *  而对象初始化就不一样了。通常，我们在 new 一个新对象的时候，都会调用它的构造方法，就是 <init>，用来初始化对象的属性。每次新建对象的时候，都会执行。
 * @author <a href="mailto:chenming1@eversec.cn">chenming</a>
 * @version 1.0.0
 * @Type TestExtendInit.java
 * @Desc
 * @date 2022/1/24 16:27
 */
public class TestExtendInit {
    public static void main(String[] args) {
        // 面试题: <clinit> 方法和 <init> 方法有什么区别？
        // JVM 会保证在子类的初始化方法执行之前，父类的初始化方法已经执行完毕
        // 1.先初始化父类静态代码块,再初始化子类代码块
        // 2.先初始化父类实例，再初始化子类实例
        // 3.静态变量，静态代码块 优先级比实例化优先级高
        // 4.静态代码块只执行一次，父类和子类构造函数执行次数由实例化次数决定

        ClassA classA = new ClassB();
        classA = new ClassB();
    }
}
