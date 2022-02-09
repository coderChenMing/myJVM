# 字节码解析
##1.文件魔数:凡是Java字节码文件前四个字节一定是cafebabe

   ![](./stage01/class_resolve/01.png)
    
   ![](./stage01/class_resolve/02.png)
    
   ![](./stage01/class_resolve/03.png)
    
##2.魔数之后四个字节:class文件次版本号+

   ![此版本号](./stage01/class_resolve/04.png)
   
   ![主版本号](./stage01/class_resolve/05.png)
    
##3.常量池
###3.1常量池大小

   ![常量池大小](./stage01/class_resolve/const_pool_01.png)
   
   如图:00 88 = 16*8 + 8 = 136 个
   通过字节码反汇编器jclasslib反汇编查看常量索引编号从001-135
   
   ![常量池大小](./stage01/class_resolve/const_pool_02.png)
   
   其中索引编号000
   在制定 class 文件格式规范的时候,设计者将第 0 项常量空了出来,为了满足后面某些指向常量池的索引值的数据在特定情况下需要表达“不引用任何一个常量池项目”的含义,这种情况可用索引值 0 来表示。
   需要注意的是,class 文件中只有常量池的容量计数是从 1 开始的,其他的集合类型（注意,这可不是指 java 集合）都是从 0 开始。
###3.2 常量格式
   在具体翻译一个个常量之前,我们现在了解一下翻译的规则。  
   class 文件中只有两种数据类型,无符号数和表。
   无符号数：基本的数据类型,可选值有 u1、u2、u4、u8,分别代表 1 个字节、2 个字节、4 个字节和 8 个字节。
   表：多个无符号数或者其他表作为数据项构成的复合数据类型,所有表都习惯性地以“_info"结尾。
   我们再回过头来看,可以发现前面介绍的魔数、版本以及常量池大小,分别可以用 u4（魔数）、u2（次版本号）、u2（主版本号）、u2（常量池大小）来表示。
   表的类型有很多,这里贴一张结构表,后面翻译常量的时候会频繁用到：
   
   ![常量格式](stage01/class_resolve/const_table.png)
   
   ![常量格式](stage01/class_resolve/const_table_type.png)
    
###3.3常量集合
   常量池入口指定了常量池的大小为 136,接下来我们一个个读取这些常量。
   [0a 00 1d 00 5c ]:0x0a = 10 表示类型是 Methodref,接下来 0x001d =29、0x005c=92,表示指向的是第 29 个和第 92 个常量。我猜这是为了缩减 class 文件的大小,故将相同的常量以地址指向的形式来引用。
   
   ![CONSTANT_Methodref_info](./stage01/class_resolve/const_pool_04.png)
   
   [09 00 06 00 5e  ]:0x09 = 9 表示类型是 Fieldref,后面 0x0006 和 0x005e 表示分别指向第 6 个和第 94 个常量。
   
   ![CONSTANT_Fieldref_info ](./stage01/class_resolve/const_pool_05.png)
   
   [07 00 61 ]:0x07 = 7 表示类型是 Class,引用指向第 97 个常量。
   
   ![CONSTANT_class_info ](./stage01/class_resolve/const_pool_06.png)
   
   [08 00 70 ]:0x08 = 8 表示类型是字符串类型,引用指向 112
   
   ![CONSTANT_String_info](./stage01/class_resolve/const_pool_07.png)
   
   [01 00 04 4e 6f 64 65 ]：0x01 = 1 表示类型是 Utf8,字符串的长度为 0x0004 = 4,故需要读取后面四个字节:4e 6f 64 65。十六进制数字 4e 6f 64 65对应的字符串为Node,这就是定义的变量名。
   
   ![CONSTANT_Utf8_info](./stage01/class_resolve/const_pool_08.png)
   
 ##4.访问标志:
   常量池结束之后,紧接着的两个字节代表访问标志（access_flags）.
   访问标志用于识别一些类或者接口层次的访问信息,包括：这个class是类还是接口,是否定义为public类型,是否定义为abstract类型,如果是类的话,是否被声明为final等。
   
   ![访问标志01](./stage01/class_resolve/access_flag.png)
   
   ![访问标志02](./stage01/class_resolve/access_flag_02.png)
   
   ![访问标志03](./stage01/class_resolve/access_flag_03.png)
   
   0x21 的二进制为 100001 ,表示 ACC_SUPER 和 ACC_PUBLIC 为 1。这与代码情况也是一致的,我们是使用 JDK1.8 编译的,并且 MyLinkedList 类确为 public 类型。
   
  ##5.类索引、父类索引和接口索引集合
  ###5.1 类索引[00 1c]
  
  ![类索引](./stage01/class_resolve/class_index_01.png)
  
  指向常量池28,常量28又指向常量120,值为com/learn/MyLinkedList
  
  ###5.2 父类索引[00 1d]
  
  ![父类索引](./stage01/class_resolve/class_index_02.png)
  
  指向常量池29,常量29又指向常量121,值为com/learn/AbstractGenericList
  
  ###5.3 接口索引[00 00]
  类索引和父类索引之后,紧接着的两个字节存储的是一个 u2 类型的数据,表示接口计数器。这里是[00,00],长度为 0 表示没有实现接口。
  
  ![接口索引](./stage01/class_resolve/class_index_03.png)
   
 ##6.字段表集合
  字段表结构和字段访问标识:
  
  ![字段表结构](./stage01/class_resolve/field_struct_01.png)
  
  ![字段访问标识](./stage01/class_resolve/field_access_flag_01.png)
  
  ![字段访问标识](./stage01/class_resolve/field_access_flag_02.png)
  
  字段数量:
  
  ![字段数量](./stage01/class_resolve/field_01.png)
  
  access_flags: 这里存储的数据是 0x0002,换算成二进制为 10,表示这个字段由 private 修饰。
  
  ![access_flags](./stage01/class_resolve/field_02.png)
  
  name_index: 0x0020 换成10进制 32,从第 32 个常量处取得字段名称 first
  
  ![name_index](./stage01/class_resolve/field_03.png)
  
  descriptor_index: 0x0021 换成10进制 33,从第 33 个常量处取得字段类型 <Lcom/learn/MyLinkedList$Node;>
  
  ![descriptor_index](./stage01/class_resolve/field_04.png)
  
  使用jclasslib反汇编之后:
  
  ![descriptor_index](./stage01/class_resolve/field_05.png)
  
  字段类型描述符与其对应类型对照表如下:
  
  ![descriptor_index](./stage01/class_resolve/field_type_desc.png)
 
 ##7. 属性表集合
 每个字段最后面,即 descriptor_index 之后,都会跟一个属性表集合。
 这里存储的值为 [00,00],表示没有其他的属性。
 如果定义的字段为 private int i = 1314;,那么这里就会存在一个属性,指向常量 1314。

##8.方法表集合
###8.1 方法数量:0x000e:14个方法=13个方法+一个无参构造方法

![方法数量](./stage01/class_resolve/method_01.png)
使用jclasslib反汇编显示:
![方法数量](./stage01/class_resolve/method_02.png)
###8.2 方法表

![方法访问标识](./stage01/class_resolve/method_access_flag_01.png)

access_flag:0x0001 private

![access_flag](./stage01/class_resolve/method_03.png)

name_index:0x0024:36

![name_index](./stage01/class_resolve/method_04.png)

descriptor_index:0x0025:37

![descriptor_index](./stage01/class_resolve/method_05.png)

最终结果 public <init> ()V
表示这是一个构造方法,()V 表示返回值类型是 void。
这与我们平常写 java 代码时的语法有点区别,java 代码中构造方法是没有返回值的,想来是因为构造方法都没有返回值,所以让编译器在编译时处理进而方便开发人员,也略微减小了 java 源文件的大小。
###8.3 属性表集合

###8.4 另一个方法

##9.附加属性集合