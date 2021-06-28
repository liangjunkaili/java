package lock;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition await和signal
 * Object wait和notify
 */
public class LockUtil {
    Object o = new Object();
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        for (int i=0;i<5;i++){
            new Thread(new Job(lock)).start();
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lock.getQueueLength());
        System.out.println(lock.hasQueuedThreads());
    }

    private void get(Queue<String> queue) throws InterruptedException {
        if (queue.isEmpty()){
            o.wait();
        }
    }
    private void put(){
        o.notify();
    }
    private static class Job implements Runnable{
        private ReentrantLock lock;
        private Condition isEmpty;
        public Job(ReentrantLock lock){
            this.lock = lock;
            isEmpty = lock.newCondition();
        }
        @Override
        public void run() {
            lock.lock();
            lock.lock();
            try {
                Random random = new Random();
                isEmpty.signal();
                int i = random.nextInt(1000);
                Thread.sleep(i);
                System.out.println("============="+i);
                System.out.println(lock.getHoldCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                lock.unlock();
            }
        }
    }
}
