package com.learn.str.pool;

/**
 * Project: jvm-learn
 * File Created at 2022-02-08 11:03:11:03
 * {@link}
 *
 * @author <a href="mailto:chenming1@eversec.cn">chenming</a>
 * @version 1.0.0
 * @Type StringTest.java
 * @Desc
 * @date 2022/2/8 11:03
 */
public class StringTest {
    public static void main(String[] args) {
        String s1 = new String("Java");// 堆中
        String s2 = s1.intern();//s2指向字符串常量池
        String s3 = "Java";//s3指向字符串常量池
        System.out.println(s1 == s2);//false
        System.out.println(s2 == s3);//true
    }
}
