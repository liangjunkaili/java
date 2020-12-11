package async;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,() -> {
            for (String k : map.keySet()) {
                System.out.println(k);
            }
            System.out.println("all over..."+map.size());
        });
        test1(cyclicBarrier,map);
    }
    public static void test1(CyclicBarrier cyclicBarrier,ConcurrentHashMap<String,Integer> map){
        CompletableFuture.supplyAsync(() -> {
            System.out.println("run 1....");
            map.put("1",1);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            return 1;
        });
        CompletableFuture.supplyAsync(() -> {
            System.out.println("run 2....");
            map.put("2",1);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            return 2;
        });
        CompletableFuture.supplyAsync(() -> {
            System.out.println("run 3....");
            map.put("3",1);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            return 3;
        });
    }
}
