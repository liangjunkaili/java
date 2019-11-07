package queue;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 链表
 * 循环数组
 */
public class QueueUtil {
    public static void main(String[] args) {
        Queue queue = new ArrayDeque();
        queue.add(1);
        ((ArrayDeque) queue).push(2);
        queue.offer(3);
        queue.removeIf((t) -> {
            System.out.println(t);
            return t.equals(1);
        });
        queue.spliterator().tryAdvance((t) -> System.out.println(t+"=="));
        queue.iterator().forEachRemaining((t) -> {
            System.out.println(t+"--");
        });
    }
}
