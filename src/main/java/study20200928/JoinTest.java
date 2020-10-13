package study20200928;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JoinTest {
    static Object o = new Object();
    static int size = 0;
    public static void main(String[] args) {
       ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        executorService.execute(()->{
            for (int i=0;i<15;i++){
                produce();
            }
        });
        executorService.execute(()->{
            for (int i=0;i<10;i++){
                consume();
            }
        });

    }

    public static void produce(){
        synchronized (o){
            try {
                while (size==10)
                    o.wait();
                System.out.println("produce: "+(size++));
                o.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void consume(){
        synchronized (o){
            try {
                while (size==0)
                    o.wait();
                System.out.println("consume: "+(size--));
                o.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}