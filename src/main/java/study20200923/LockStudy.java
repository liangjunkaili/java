package study20200923;

import study20200907.ThreadPool;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读操作有必要加锁吗？
 */
public class LockStudy {
    //传true表示是公平锁
    static ReentrantLock lock = new ReentrantLock();//默认是不公平的
    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();//默认是不公平的
    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            ThreadPool.THREAD_POOL.execute(LockStudy::read);
        }
        for (int i=0;i<10;i++){
            ThreadPool.THREAD_POOL.execute(LockStudy::write);
        }
    }

    public static void read(){
//        lock.lock();
//        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"--read ");
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"--read end");
//            lock.unlock();
//            readWriteLock.readLock().unlock();
        }
    }
    public static void write(){
//        lock.lock();
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"--write ");
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"--write end");
//            lock.unlock();
            readWriteLock.writeLock().unlock();
        }
    }
}
