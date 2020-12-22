package bytecode;

/**
 * i++ 和 ++i
 */
public class ByteCodeDemo {
    public static void main(String[] args) {
        foo();
        foo2();
        bar();
    }

    /**
     * 关键部分的字节码
     * 10: iload_0：将局部变量表slot=0的变量（i）加载到操作数栈上
     * 11: iinc          0, 1：对局部变量表slot=0的变量（i）直接加1
     * 14: istore_0：将栈顶元素出栈赋值给局部变量表slot=0的变量（i）
     */
    public static void foo(){
        int i=0;
        for (int j=0;j<50;j++){
            i = i++;
        }
        System.out.println(i);
    }

    /**
     *  10: iinc          0, 1:对局部变量表slot=0的变量（i）直接加1
     *  13: iload_0：将局部变量表slot=0的变量（i）加载到操作数栈上
     *  14: istore_0：将栈顶元素出栈赋值给局部变量表slot=0的变量（i）
     */
    public static void foo2(){
        int i=0;
        for (int j=0;j<50;j++){
            i = ++i;
        }
        System.out.println(i);
    }

    /**
     *  1: istore_0
     *  2: iload_0
     *  3: iinc          0, 1
     *  6: iinc          0, 1
     *  9: iload_0
     *  10: iadd
     *  11: istore_0
     */
    public static void bar(){
        int i=0;
        i = i++ + ++i;
        System.out.println(i);
    }
}
