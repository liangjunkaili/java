package study20200928;

import java.util.ArrayList;
import java.util.List;


public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("hello world");
        List<String> strings = new ArrayList<>();
        for (String s:strings){
            System.out.println(s);
        }
    }
    public String concat(String s1,String s2,String s3){
        return s1+s2+s3;
    }
    public synchronized static void lock1(){

    }
    public void lock2(){
        synchronized (this){

        }
    }
}