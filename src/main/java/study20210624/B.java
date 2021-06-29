package study20210624;

public class B {
    static A a;
    static {
        a = new A();
        System.out.println("B");
    }
    public B(){
        System.out.println(a);
    }
}
