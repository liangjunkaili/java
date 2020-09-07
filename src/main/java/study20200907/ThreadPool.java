package study20200907;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 关于如何定义一个合适的线程池？
 * 1、确定任务的类型？io密集 or CPU密集
 *  IO密集型的特点：任务执行耗时较长，CPU占用时间短，可适当增大线程数
 *  CPU密集型的特点：任务执行耗时短，CPU占用时间短，可与CPU数一样
 * 2、各参数的设定
 *  核心线程数与最大的线程数一样
 *  用什么样的队列：要使用有界的队列
 *
 */
public class ThreadPool {
    public final int corePoolSize = Runtime.getRuntime().availableProcessors();//核心线程池的大小
    int maximumPoolSize = corePoolSize;//最大的线程池的大小
    long keepAliveTime = 30;//线程池中超过corePoolSize的空闲线程的最大存活时间
    TimeUnit timeUnit = TimeUnit.MILLISECONDS;//keepAliveTime的时间单位
    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();//阻塞任务队列
    ThreadFactory threadFactory;//新建线程工厂
    RejectedExecutionHandler handler;//当任务数超过maximumPoolSize+workQueue时，会交给RejectedExecutionHandler处理
    AtomicInteger count = new AtomicInteger(0);
    public final ExecutorService THREAD_POOL = new ThreadPoolExecutor(
            corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            timeUnit,
            workQueue,
            (r) -> new Thread(r,"MyThreadPool--"+count.addAndGet(1)),
            (r,executor) -> System.out.println("被拒绝的任务："+r.toString()));

    public static void main(String[] args) {
//        ExecutorService pool = new ThreadPool().THREAD_POOL;
//        for (int i=0;i<100;i++){
//            pool.execute(() -> System.out.println(Thread.currentThread().getName()+"is running..."));
//        }
        System.out.println(15635382901L % 2);
        System.out.println(15635382902L % 2);
        System.out.println(15635382903L % 2);
        System.out.println(15635382904L % 2);
        System.out.println(15635382909L % 2);
    }
}
