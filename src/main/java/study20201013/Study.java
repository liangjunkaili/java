package study20201013;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Study {
    public static void main(String[] args) {
//        Map<String,String> map = new ConcurrentHashMap<>(3);
//        System.out.println(map.put("a","a"));
//        System.out.println(map.put("b","b"));
//        System.out.println(map.put("c","c"));
//        System.out.println(map.put("d","d"));
//        System.out.println(map.size());

        Study study = new Study(1);
        study.doTest(true);
        study.doTest(false);
    }
    private volatile double l;
    private int n;
    private int[] input;

    public Study(int n) {
        this.n = n;
        input = new int[n];
        Random random = new Random();
        for (int i=0;i<n;i++){
            input[i] = random.nextInt(100);
        }
    }

    public void doTest(boolean isWarmUp){
        long begin = System.currentTimeMillis();
        for (int i=0;i<n;i++){
            l = fib(input[i]);
        }
        if (!isWarmUp){
            long end = System.currentTimeMillis();
            System.out.println("time:"+(end-begin));
        }
    }

    private double fib(int i) {
        if (i<0) throw  new IllegalArgumentException("<0");
        if (i<=1)return i;
        return fib(i-2)+fib(i-1);
    }
}
