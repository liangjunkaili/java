package study20200921;

public class JvmStudy {
    static int a=0;
    static {
        a=1;
        b=1;
    }
    static int b=0;
    public static void main(String[] args) {
        {

        }
        byte[] placeholder = new byte[64*1024*1024];
        System.out.println(a);
        System.gc();
    }
}
