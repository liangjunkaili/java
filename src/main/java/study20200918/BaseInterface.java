package study20200918;

public interface BaseInterface {
    int a=1;
    static void say(){
        System.out.println("interface say");
    }
    default void hello(){
        System.out.println("interface hello");
    }
    void empty();
}
