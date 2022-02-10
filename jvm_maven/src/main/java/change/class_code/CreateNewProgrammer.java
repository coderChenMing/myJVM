package change.class_code;

import com.sun.org.apache.bcel.internal.Repository;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ClassGen;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LDC;
import com.sun.org.apache.bcel.internal.generic.MethodGen;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.org.apache.bcel.internal.generic.Type;

import java.io.IOException;

/**
 * Project: myJVM
 * File Created at 2022-02-10 11:04:11:04
 * {@link}
 *
 * @author <a href="mailto:charmFlightChen@gmail.com">chenming</a>
 * @version 1.0.0
 * @Type CreateNewProgrammer.java
 * @Desc
 * @date 2022/2/10 11:04
 */
public class CreateNewProgrammer {
    public static void main(String[] args) throws IOException {
        // 1.使用BCEL加载需要编辑的class文件
        JavaClass clazz = Repository.lookupClass(Programmer.class);
        ClassGen classGen = new ClassGen(clazz);
        //获取常量池信息
        ConstantPoolGen cPoolGen = classGen.getConstantPool();
        // 2.在常量池中增加一个MethodRef doPlanning
        //在常量池中增加一个方法的声明返回methodIndex为声明在常量池中的位置索引
        //第一个参数是路径类名
        //第二个参数是方法名称
        //第三个方法返回类型 ()V 是void类型
        //方法返回类型描述参考
        int methodIndex = cPoolGen.addMethodref("change.class_code.Programmer", "doPlanning", "()V");
        // 3.在常量池中增加一个String类型的Filed
        // 因为有System.out.println("doPlanning")语句
        // doPlanning 中的System.out 变量和println方法再doCoding中已经使用,所以已经在常量池中了
        int stringIndex = cPoolGen.addString("doPlanning...");
        // 注意这里需要记录追加方法和Filed的index后面需要使用。

        // 4.然后创建 doPlanning 方法的实体的字节码指令
        InstructionList instructionDoPlan = new InstructionList();  // 字节码指令信息
        instructionDoPlan.append(new GETSTATIC(17));  // 获取System.out常量
        instructionDoPlan.append(new LDC(stringIndex));  // 获取String Field信息
        instructionDoPlan.append(new INVOKEVIRTUAL(25)); // 调用Println方法
        instructionDoPlan.append(new RETURN());    // return 结果
        // 5. 生成 doPlanning 方法
        MethodGen doPlanMethodGen = new MethodGen(1, Type.VOID, Type.NO_ARGS, null, "doPlanning",
            classGen.getClassName(), instructionDoPlan, cPoolGen);
        classGen.addMethod(doPlanMethodGen.getMethod());
        // 方法的声明并追加到classGen中。
        // 这样 doPlanning 方法就追加成功了。接下来我们需要找到 doCoding 方法，在方法中追加对 doPlanning 的调用。
        // 6.找到并修改 doCoding 方法
        Method[] methods = classGen.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if ("doCoding".equals(methodName)) {
                MethodGen methodGen = new MethodGen(method, clazz.getClassName(), cPoolGen);
                InstructionList instructionList = methodGen.getInstructionList();
                InstructionHandle[] handles = instructionList.getInstructionHandles();
                InstructionHandle from = handles[0];
                InstructionHandle aload = instructionList.append(from, new ALOAD(0));
                instructionList.append(aload, new INVOKESPECIAL(methodIndex));
                classGen.replaceMethod(method, methodGen.getMethod());
            }
        }
        // InstructionList 是当前方法中的字节码指令，我们append了两个指令 ALOAD 和 INVOKESPECIAL 。实现 doPlanning 的调用。
        // 7. 将编辑后的Class输出
        JavaClass target = classGen.getJavaClass();
        target.dump("d:\\test\\change\\class_code\\Programmer.class");
    }
}
