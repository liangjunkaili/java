package study20200923;

import study20200907.ThreadPool;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CustomReentrantLock {

    static CustomReentrantLock lock = new CustomReentrantLock();
    public static void main(String[] args) {
        ThreadPool.THREAD_POOL.execute(CustomReentrantLock::process);
        ThreadPool.THREAD_POOL.execute(CustomReentrantLock::process);
    }
    public static void process(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"--process ");
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"--process end");
            lock.unlock();
        }
    }
    AtomicReference<Thread> reference = new AtomicReference<>();
    AtomicInteger count = new AtomicInteger(0);
    public void lock(){
        Thread t = Thread.currentThread();
        if (t==reference.get()){
            count.incrementAndGet();
            return;
        }
        while (!reference.compareAndSet(null,t)){
            System.out.println("wait...");
        }
    }
    public void unlock(){
        Thread t = Thread.currentThread();
        if (t==reference.get()){
            if (count.get()>0){
                count.decrementAndGet();
            }else {
                reference.set(null);
            }
        }
    }
}
