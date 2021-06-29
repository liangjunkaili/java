package study20210624;

public class A {
    static B b;
    static {
        b = new B();
        System.out.println("A--");
    }
    public A(){
        System.out.println("--"+b);
//        System.out.println("-----");
    }
}
