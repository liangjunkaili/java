package study20200918;

public class Base extends BaseAbstractClass implements BaseInterface{
    public static final int a = 1;
    public static transient int b = 1;
    public transient int c = 1;
    public void test(int a){

    }
    static {
        System.out.println(3333);
    }
    {
        System.out.println("4444");
    }
    @Override
    public void empty() {
        System.out.println("class empty");
    }

    @Override
    public void hello() {
        System.out.println("class hello");
    }

    public static void main(String[] args) {
        Base b = new Base();
        b.empty();
        b.hello();
        b.d2();
        Base.d1();
    }

    @Override
    public void d2() {

    }
}
