package study20200807;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 步骤：
 * 1、创建CompletableFuture
 * 2、计算结果完成时的处理
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        Executor executor = Executors.newSingleThreadExecutor();

        CompletableFuture<Integer> completableFuture1 =  CompletableFuture.completedFuture(1);
        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(()->System.out.println());//ForkJoinPool.commonPool()
        CompletableFuture<Void> completableFuture3 = CompletableFuture.runAsync(()->System.out.println(),executor);
        CompletableFuture<String> completableFuture4 = CompletableFuture.supplyAsync(() -> "");
        CompletableFuture<String> completableFuture5 = CompletableFuture.supplyAsync(() -> "",executor);

        completableFuture1.whenComplete((t,u) -> {
            System.out.println(t);
            System.out.println(u);
        });
        completableFuture2.whenCompleteAsync((t,u) -> {
            System.out.println(t);
            System.out.println(u);
        });
        completableFuture3.whenCompleteAsync((t,u) -> {
            System.out.println(t);
            System.out.println(u);
        },executor);
        completableFuture4.exceptionally((t) -> "exception");

        completableFuture5.handle((t,u) -> "----");
        completableFuture5.handleAsync((t,u) -> "----");
        completableFuture5.handleAsync((t,u) -> "----",executor);

        completableFuture1.thenApply((t) -> 1);
        completableFuture1.thenApplyAsync((t) -> 1);
        completableFuture1.thenApplyAsync((t) -> 1,executor);

        completableFuture2.thenAccept(System.out::println);
        completableFuture2.thenAcceptAsync(System.out::println);
        completableFuture2.thenAcceptAsync(System.out::println,executor);

        completableFuture3.thenAcceptBoth(completableFuture1,(a,b) -> {
            System.out.println(a+"="+b);
        });
        completableFuture3.thenAcceptBothAsync(completableFuture1,(a,b) -> {
            System.out.println(a+"="+b);
        });
        completableFuture3.thenAcceptBothAsync(completableFuture1,(a,b) -> {
            System.out.println(a+"="+b);
        },executor);

        completableFuture3.runAfterBoth(completableFuture1,System.out::println);
        completableFuture3.runAfterBothAsync(completableFuture1,System.out::println);
        completableFuture3.runAfterBothAsync(completableFuture1,System.out::println,executor);

        completableFuture4.thenRun(()->{
            //doSomething
        });
        completableFuture4.thenRunAsync(()->{
            //doSomething
        });
        completableFuture4.thenRunAsync(()->{
            //doSomething
        },executor);

        completableFuture5.thenCompose((t) -> {
            System.out.println(t);
            return null;
        });
        completableFuture5.thenComposeAsync((t) -> {
            System.out.println(t);
            return null;
        });
        completableFuture5.thenComposeAsync((t) -> {
            System.out.println(t);
            return null;
        },executor);

        completableFuture1.thenCombine(completableFuture2,(a,b) -> 1);
        completableFuture1.thenCombineAsync(completableFuture2,(a,b) -> 1);
        completableFuture1.thenCombineAsync(completableFuture2,(a,b) -> 1,executor);

        completableFuture2.acceptEither(completableFuture3,System.out::println);
        completableFuture2.acceptEitherAsync(completableFuture3,System.out::println);
        completableFuture2.acceptEitherAsync(completableFuture3,System.out::println,executor);

        completableFuture4.applyToEither(completableFuture5,t -> "");
        completableFuture4.applyToEitherAsync(completableFuture5,t -> "");
        completableFuture4.applyToEitherAsync(completableFuture5,t -> "",executor);

        CompletableFuture<Void> completableFuture6 =
                CompletableFuture.allOf(completableFuture1,completableFuture2,completableFuture3);
        CompletableFuture<Object> completableFuture7
                = CompletableFuture.anyOf(completableFuture1,completableFuture2,completableFuture3);
    }
}
