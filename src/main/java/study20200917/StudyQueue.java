package study20200917;

import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 队列：
 *  顺序队列
 *      循环队列
 *  链式队列
 *  阻塞队列
 *  并发队列
 */
public class StudyQueue {
    final Stack a;
    final Stack b;
    public StudyQueue(){
        a = new Stack();
        b = new Stack();
    }
    public void appendTail(int value) {
        a.push(value);
    }

    public int deleteHead() {
        if (!b.empty()){
            return (int) b.pop();
        }
        if (a.empty())
            return -1;
        else {
            while (!a.empty()){
                b.push(a.pop());
            }
        }
        return (int) b.pop();
    }
    public int fib(int n,int[] a) {
        if(n<2)
            return n;
        if (a[n]!=0)
            return a[n];
        int n1 = fib(n-1,a);
        a[n-1] = n1;
        int n2 = fib(n-2,a);
        a[n-2] = n2;
        int n3 = n1+n2;
        a[n] = n3;
        return n3;
    }
    public int fibV2(int n) {
        int a=0,b=1,sum;
        for (int i=0;i<n;i++){
            sum = a+b;
            a = b;
            b = sum;
        }
        return a;
    }
    public static void main(String[] args) {
        StudyQueue queue = new StudyQueue();
        int n = 44;
        int[] a = new int[n+1];
        System.out.println(queue.fib(n,a));
        System.out.println(queue.fibV2(n));
//        for (int i = 0; i < 4; i++) {
//            queue.appendTail(i);
//        }
//        for (int i = 0; i < 6; i++) {
//            System.out.println(queue.deleteHead());
//        }
//        ArrayQueue queue = new ArrayQueue(5);
//        CycleQueue queue = new CycleQueue(5);
//        LinkedQueue queue = new LinkedQueue();
//        ArrayBlockingQueue queue = new ArrayBlockingQueue(5);
//        ArrayBlockingQueueV2 queue = new ArrayBlockingQueueV2(5);
//        for (int i = 0; i < 6; i++) {
//            System.out.println(queue.add(i));
//        }
//        for (int i = 0; i < 6; i++) {
//            System.out.println(queue.get());
//        }
//        for (int i = 0; i < 3; i++) {
//            System.out.println(queue.add(i));
//        }
//        for (int i = 0; i < 6; i++) {
//            System.out.println(queue.get());
//        }
//        ExecutorService service =
//                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        service.execute(() -> {
//            while (true) {
//                for (int i = 0; i < 6; i++) {
//                    System.out.println(Thread.currentThread().getName());
//                }
//                System.out.println(queue.add(1));
//            }
//        });
//        service.execute(() -> {
//            while (true) {
//                for (int i = 0; i < 6; i++) {
//                    System.out.println(Thread.currentThread().getName());
//                }
//                System.out.println(queue.add(2));
//            }
//        });
//        service.execute(() -> {
//            while (true) {
//                System.out.println(Thread.currentThread().getName()+"--"+queue.get());
//            }
//        });
    }
    static class ArrayQueue{
        int[] data;
        int size;
        int head;
        int tail;
        public ArrayQueue(int size){
            data = new int[size];
            this.size = size;
        }
        public boolean add(int i){
            if (tail==size){
                if (head==0)
                    return false;
                for (int j=head;j<tail;j++){
                    data[j-head] = data[head];
                }
                tail = tail-head;
                head=0;
            }
            data[tail] = i;
            tail++;
            return true;
        }
        public int get(){
            if (head==tail)
                return -1;
            return data[head++];
        }
    }
    static class ArrayBlockingQueue{
        int[] data;
        int size;
        int head;
        int tail;
        Object o1 = new Object();
        public ArrayBlockingQueue(int size){
            data = new int[size];
            this.size = size;
        }
        public boolean add(int i){
            synchronized (o1){
                if (tail==size){
                    if (head==0) {
                        try {
                            o1.wait(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                    for (int j=head;j<tail;j++){
                        data[j-head] = data[head];
                    }
                    tail = tail-head;
                    head=0;
                }
                data[tail] = i;
                tail++;
                o1.notify();
            }
            return true;
        }
        public int get(){
            synchronized (o1){
                if (head==tail){
                    try {
                        o1.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return -1;
                }
                int v = data[head++];
                o1.notify();
                return v;
            }
        }
    }
    static class ArrayBlockingQueueV2{
        int[] data;
        int size;
        int head;
        int tail;
        final ReentrantLock lock;
        final Condition notEmpty;
        final Condition notFull;
        public ArrayBlockingQueueV2(int size){
            data = new int[size];
            this.size = size;
            lock = new ReentrantLock();
            notEmpty = lock.newCondition();
            notFull = lock.newCondition();
        }
        public boolean add(int i){
            lock.lock();
            try{
                if (tail==size){
                    if (head==0) {
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                    for (int j=head;j<tail;j++){
                        data[j-head] = data[head];
                    }
                    tail = tail-head;
                    head=0;
                }
                data[tail] = i;
                tail++;
                notEmpty.signal();
                return true;
            }finally {
                lock.unlock();
            }
        }
        public int get(){
            lock.lock();
            try{
                if (head==tail){
                    try {
                        notEmpty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return -1;
                }
                int v = data[head++];
                notFull.signal();
                return v;
            }finally {
                lock.unlock();
            }
        }
    }
    static class CycleQueue{
        int[] data;
        int size;
        int head;
        int tail;
        public CycleQueue(int size){
            data = new int[size];
            this.size = size;
        }
        public boolean add(int i){
            if ((tail+1)%size==head){
                return false;
            }
            data[tail] = i;
            tail = (tail+1)%size;
            return true;
        }
        public int get(){
            if (head==tail)
                return -1;
            int v = data[head];
            head = (head+1)%size;
            return v;
        }
    }
    //如何表示一个空的链表？
    static class LinkedQueue{
        Node head = new Node(-1);
        Node tail = head;
        public boolean add(int i){
            tail.next = new Node(i);
            tail = tail.next;
            return true;
        }
        public int get(){
            if (head.next==null)
                return -1;
            Node n = head.next;
            head = head.next;
            return n.val;
        }
    }
    static class Node{
        int val;
        Node next;
        public Node(int val){
            this.val = val;
        }
    }
}
