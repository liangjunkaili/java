package study20200914;

public class Parent {
    static {
        System.out.println("parent static");
    }
    public static int v = 1;
    public int a = getA();
    public Parent(){
        System.out.println("parent");
    }
    private int getA(){
        System.out.println("getA()");
        return 5;
    }
    public static void main(String[] args) {
        Child child = new Child();
//        System.out.println(child.a);
//        System.out.println(Child.v);
    }
}
class Child extends Parent{
    static {
        System.out.println("child static");
    }
    public static int v = 2;
    public int a = getA();
    private int getA(){
        System.out.println("child getA()");
        return 7;
    }
    public Child(){
        System.out.println("child");
    }
}