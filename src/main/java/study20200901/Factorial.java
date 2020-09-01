package study20200901;

/**
 * 阶乘
 * 5！ = 5*4*3*2*1
 */
public class Factorial {
    public static void main(String[] args) {
        System.out.println(fact(5));
    }
    public static int fact(int n){
        if (n==1)
            return 1;
        return fact(n-1)*n;
    }
}
