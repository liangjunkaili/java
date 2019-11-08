package lock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairAndUnFairTest {
    public static void main(String[] args) {
        FairAndUnFairTest fairAndUnFairTest = new FairAndUnFairTest();
        fairAndUnFairTest.fair();
//        fairAndUnFairTest.unfair();
    }
    private static Lock fairLock = new ReentrantLock2(true);
    private static Lock unfairLock = new ReentrantLock2(false);

    public void fair(){
        testLock(fairLock);
    }
    public void unfair(){
        testLock(unfairLock);
    }

    private void testLock(Lock lock){
        for (int i=0;i<5;i++){
            Job j = new Job(lock);
            j.setDaemon(true);
            j.start();
        }
    }

    private static class Job extends Thread{
        private Lock lock;
        public Job(Lock lock){
            this.lock = lock;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            List<Thread> threads = new ArrayList<>(((ReentrantLock2)lock).getQueuedThreads());
            for (Thread t: threads
                 ) {
                System.out.println(t.currentThread().getName()+","+threads.toArray().toString());
            }
        }
    }
    private static class ReentrantLock2 extends ReentrantLock{
        public ReentrantLock2(boolean fair){
            super(fair);
        }
        public Collection<Thread> getQueuedThreads(){
            List<Thread> threads = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(threads);
            return threads;
        }
    }
}
