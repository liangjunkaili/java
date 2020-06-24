package async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName Async
 * @Description TODO
 * @Author junliang
 * @Date 2019/11/22 6:29 PM
 * @Version 1.0
 **/
public class Async {
    public static void async(){
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });

        CompletableFuture other = CompletableFuture.runAsync(() -> {
            System.out.println("other...");
        });

        future.thenApply(t -> {
            System.out.println(t);
            return 1;
        });

        future.thenAccept(t ->{
            System.out.println(t);
        });

        future.thenRun(() -> {
            System.out.println("run...");
        });

        future.handle((a,e) -> {
            System.out.println(a+"-"+e);
            return 2;
        });

        future.thenCompose(t -> {
            System.out.println(t);
            return future.toCompletableFuture();
        });

        future.thenCombine(other,(a,b) -> {
            System.out.println(a+"-"+b);
            return 3;
        });

        future.thenAcceptBoth(other,(a,b) -> {
            System.out.println(a+"-"+b);
        });

        future.whenComplete((a,e) -> {
            System.out.println(a+"-"+e);
        });

        future.runAfterBoth(other,() -> {
            System.out.println("runAfterBoth");
        });

        future.applyToEither(other,(a)->{
            System.out.println(a);
            return 4;
        });

        future.acceptEither(other,(a) ->{
            System.out.println(a);
        });

        future.runAfterEither(other,()->{
            System.out.println("runAfterEither");
        });

        future.exceptionally((e) -> {
            System.out.println(e);
            return 5;
        });

        CompletableFuture all = CompletableFuture.allOf(future,other);
        CompletableFuture any = CompletableFuture.anyOf(future,other);
        all.join();
    }

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        async();
        System.out.println(System.currentTimeMillis() - begin);
    }
}
