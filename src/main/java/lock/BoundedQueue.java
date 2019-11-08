package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueue<T> {
    private Object[] items;
    private int addIndex,removeIndex,count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    public BoundedQueue(int size){
        items = new Object[size];
    }

    public void add(T t) throws InterruptedException {
        lock.lock();
        try{
            while (count==items.length)
                notFull.await();
            items[addIndex] = t;
            if (++addIndex == items.length)
                addIndex = 0;
            ++count;
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public T remove() throws InterruptedException {
        System.out.println("remove");
        lock.lock();
        try {
            while (count==0)
                notEmpty.await();
            Object c = items[removeIndex];
            if (++removeIndex == items.length)
                --count;
            removeIndex = 0;
            notFull.signal();
            return (T) c;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BoundedQueue boundedQueue = new BoundedQueue(5);
        try {
            boundedQueue.add("a");
            boundedQueue.add("a");
            boundedQueue.add("a");
            boundedQueue.add("a");
            boundedQueue.add("a");
            new Thread(() ->{
                try {
                    boundedQueue.remove();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            boundedQueue.add("a");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
