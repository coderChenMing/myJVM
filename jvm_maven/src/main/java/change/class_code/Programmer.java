package change.class_code;

/**
 * Project: myJVM
 * File Created at 2022-02-10 10:48:10:48
 * {@link}
 * 使用BCEL动态改变Class内容
 * BCEL:Apache Byte Code Engineering Library
 * BCEL 每项内容操作在JVM汇编语言的级别
 * @author <a href="mailto:charmFlightChen@gmail.com">chenming</a>
 * @version 1.0.0
 * @Type Programmer.java
 * @Desc
 * @date 2022/2/10 10:48
 */
public class Programmer implements People{
    public void doCoding() {
        System.out.println("do coding");
    }
}
