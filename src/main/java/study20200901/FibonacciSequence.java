package study20200901;

/**
 * 斐波那契数列
 * F(1)=1，F(2)=1, F(n)=F(n - 1)+F(n - 2)（n ≥ 3，n ∈ N*）
 */
public class FibonacciSequence {
    public static void main(String[] args) {
        System.out.println(f(6));
    }
    public static int f(int n){
        if (n==1||n==2)
            return 1;
        return f(n-1)+f(n-2);
    }

}
