package dataStructure;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 队列的实现
 * 循环队列(tail + 1) % n == head
 */
public class Queue {
    private  String[] items;
    private int head;
    private int tail;
    private int n;

    public Queue(int size){
        items = new String[size];
        n = size;
        head = 0;
        tail = 0;
    }
    public void enqueue(String item){
        if (n==tail){//(tail + 1) % n == head
            if (head==0){
                return;
            }
            System.out.println(head+"=="+tail);
            String[] newitems = new String[n];
            System.arraycopy(items,head,newitems,0,tail-head);
            items = newitems;
            tail = tail-head;
            head=0;
        }
        items[tail] = item;
        tail++;//tail = (tail + 1) % n;
    }
    public String dequeue(){
        if (n==head){
            return null;
        }
        String item = items[head];
        head++;//head = (head + 1) % n;
        return item;
    }

    public static void main(String[] args) {
        Queue queue = new Queue(10);
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3a");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
        queue.enqueue("7");
        queue.enqueue("8");
        queue.enqueue("9");
        queue.enqueue("a");
        System.out.println("chulaile "+queue.dequeue());
        System.out.println("chulaile "+queue.dequeue());
        System.out.println("chulaile "+queue.dequeue());
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        for (String s:queue.items){
            System.out.println(s);
        }

    }
}
