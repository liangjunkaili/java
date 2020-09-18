package study20200918;

public abstract class BaseAbstractClass {
    public static final int a=1;
    public abstract void d2();
    public static void d1(){
        System.out.println("BaseAbstractClass d1");
    }
    static {
        System.out.println("1111");
    }
    {
        System.out.println("222222222");
    }
}
