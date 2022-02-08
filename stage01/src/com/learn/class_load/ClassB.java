package com.learn.class_load;

/**
 * Project: jvm-learn
 * File Created at 2022-01-24 17:58:17:58
 * {@link}
 *
 * @author <a href="mailto:chenming1@eversec.cn">chenming</a>
 * @version 1.0.0
 * @Type ClassB.java
 * @Desc
 * @date 2022/1/24 17:58
 */
public class ClassB {
    private int a = 1234;
    static long C = 1111;

    public long test(long num) {
        long ret = this.a + C + num;
        return ret;
    }
}
