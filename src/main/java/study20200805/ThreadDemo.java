package study20200805;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;

/**
 * 子类继承父类
 * 1、子类拥有父类非 private 的属性、方法。
 * 2、子类可以拥有自己的属性和方法，即子类可以对父类进行扩展。
 * 3、子类可以用自己的方式实现父类的方法。
 * 4、子类是不继承父类的构造器（构造方法或者构造函数）的，它只是调用（隐式或显式）。
 *    如果父类的构造器带有参数，则必须在子类的构造器中显式地通过 super 关键字调用父类的构造器并配以适当的参数列表。
 *    如果父类构造器没有参数，则在子类的构造器中不需要使用 super 关键字调用父类构造器，系统会自动调用父类的无参构造器。
 * super关键字：我们可以通过super关键字来实现对父类成员的访问，用来引用当前对象的父类。
 * this关键字：指向自己的引用。
 */
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread("thread-1");
        System.out.println(thread.getState());
        thread.start();
        thread.interrupt();
        synchronized (thread){
            System.out.println("11111111");
            thread.wait();
        }
//        thread.join();
        System.out.println(thread.total);
//        Runnable runnable = new MyThread2(new MyThread("2"));
//        Thread thread2 = new Thread(runnable);
//        thread2.start();
//        thread.join();
//        thread2.join();
//        System.out.println(Thread.currentThread().getName()+" : ThreadDemo#main----------");
        Producer p = new Producer();
        new Thread(p).start();
        new Thread(new Consume(p)).start();
    }
}
class MyThread extends Thread{
    Object obj = new Object();
    //自动调用父类的无参构造函数
    public MyThread() {
    }

    //不显式调用的情况下，自动调用父类的无参构造函数
    public MyThread(String name) {
        super(name);
    }
    int total;
    @Override
    public void run() {
//        try {
            synchronized (this){
//                Thread.sleep(1000);
                for (int i = 0; i < 100; i++) {
                    total += i;
                }
                notify();
            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
class MyThread2 implements Runnable{
    private Thread thread;
    public MyThread2(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        try {
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" : MyThread2 implements Runnable----------");
    }
}
class Consume implements Runnable{
    Producer producer;
    public Consume(Producer producer){
        this.producer = producer;
    }
    @Override
    public void run() {
        while (true){
            try {
                System.out.println(producer.get());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Producer implements Runnable{

    int max = 5;
    Vector<String> vector = new Vector<>();
    public synchronized void put() throws InterruptedException {
        if (vector.size()>=5){
            wait();
        }
        System.out.println("put message...");
        vector.addElement(LocalDateTime.now().toString());
        notify();
    }
    public synchronized String get() throws InterruptedException {
        if (vector.size()==0){
            wait();
        }
        String ele = vector.firstElement();
        vector.removeElement(ele);
        notify();
        return ele;
    }
    @Override
    public void run() {
        while (true){
            try {
                put();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}