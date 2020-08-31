package study20200829;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 字符串匹配算法
 * 1、BF算法
 */
public class StringDemo {
    public static void main(String[] args) {
        String text = "abcdefghijklmn";
        String pattern = "n";
//        System.out.println(text.substring(13,14));
        System.out.println(bf(text,pattern));
    }
    public static boolean bf(String text,String pattern){
        int len = pattern.length();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i=0;i<=text.length()-len;i++){
            int end = i+len;
            end = end>text.length()?text.length():end;
            String children = text.substring(i,end);
//            System.out.println(i+"-"+end+"-"+children);
            System.out.println(atomicInteger.incrementAndGet());
            if (children.equals(pattern)){
                return true;
            }
        }
        return false;
    }
}
