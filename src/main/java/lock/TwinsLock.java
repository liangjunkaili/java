package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 该工具在同一时刻，只允许至多两个线程同时访问，超过两个线程的访问将被阻塞
 * 共享式
 * 正常状态为0、1、2
 */
public class TwinsLock implements Lock {
    private final Sync sync = new Sync(2);
    private static final class Sync extends AbstractQueuedSynchronizer {
        Sync(int count){
            if (count<=0){
                throw new IllegalArgumentException("count must large than zero.");
            }
            setState(count);
        }
        @Override
        protected int tryAcquireShared(int arg) {
            for (;;){
                int current = getState();
                int newCount = current-arg;
                if (newCount<0||compareAndSetState(current,newCount)){
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (;;){
                int current = getState();
                int newCount = current+arg;
                if (compareAndSetState(current,newCount)){
                    return true;
                }
            }
        }

        @Override
        protected boolean isHeldExclusively() {
            return isHeldExclusively();
        }

        Condition newCondition(){
            return new ConditionObject();
        }
    }
    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        int state = sync.tryAcquireShared(1);
        return state>=0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
