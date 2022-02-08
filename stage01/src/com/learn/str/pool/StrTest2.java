package com.learn.str.pool;

/**
 * Project: myJVM
 * File Created at 2022-02-08 11:28:11:28
 * {@link}
 *
 * @author <a href="mailto:chenming1@eversec.cn">chenming</a>
 * @version 1.0.0
 * @Type StrTest2.java
 * @Desc
 * @date 2022/2/8 11:28
 */
public class StrTest2 {
    public static void main(String[] args) {
        String s1 = "Ja" + "va";
        String s2 = "Java";
        System.out.println(s1 == s2);// true
    }
}
