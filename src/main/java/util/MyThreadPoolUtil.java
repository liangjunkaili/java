package util;

import java.security.PrivilegedAction;
import java.util.concurrent.*;

/**
 * @ClassName MyThreadPoolUtil
 * @Description TODO
 * @Author junliang
 * @Date 2019/12/4 5:51 PM
 * @Version 1.0
 **/
public class MyThreadPoolUtil {
    public static void create(){
        Executors.newFixedThreadPool(8);
        Executors.newCachedThreadPool();
        Executors.newSingleThreadExecutor();
        Executors.newScheduledThreadPool(8);
        Executors.newSingleThreadScheduledExecutor();
        Executors.newWorkStealingPool();
//        Executors.unconfigurableExecutorService()
        Callable callable = Executors.callable(() ->{
            System.out.println("111111");
        });
        try {
            System.out.println(callable.call());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Executors.callable(new PrivilegedAction() {
            @Override
            public Object run() {
                return null;
            }
        });
    }

    public static void main(String[] args) {
//        create();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(8,8,1000, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(1024));
//        executor.execute(()->{
//            System.out.println("1111");
//        });
//        System.out.println(executor.prestartCoreThread());
        System.out.println(executor.prestartAllCoreThreads());
//        System.out.println(executor.allowsCoreThreadTimeOut());
        System.out.println(executor.getActiveCount());
        System.out.println(executor.getCompletedTaskCount());
        System.out.println(executor.getCorePoolSize());
//        System.out.println(executor.getKeepAliveTime(TimeUnit.MILLISECONDS));
        System.out.println(executor.getLargestPoolSize());
        System.out.println(executor.getMaximumPoolSize());
        System.out.println(executor.getPoolSize());
//        System.out.println(executor.getQueue());
//        System.out.println(executor.getRejectedExecutionHandler());
        System.out.println(executor.getTaskCount());
//        System.out.println(executor.getThreadFactory());
//        System.out.println(executor.isShutdown());
//        System.out.println(executor.isTerminated());
//        System.out.println(executor.isTerminating());
    }
}
