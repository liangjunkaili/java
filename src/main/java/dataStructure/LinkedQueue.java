package dataStructure;

public class LinkedQueue{
    private static class Node{
        private String value;
        private Node next;
        public Node(String value,Node next){
            this.value = value;
            this.next = next;
        }
    }
    private Node head;
    private Node tail;
    private int n;
    public LinkedQueue(){
        head = new Node("-1",null);
        tail = head;
    }
    public void enqueue(String value){
        Node node = new Node(value,null);
//        if (head.next==null){
//            head.next = node;
//        }
        tail.next = node;
        tail = tail.next;
        n++;
    }
    public String dequeue(){
        if (head.next==null){
            return "-1";
        }
        Node node = head.next;
//        if (node.next==null){
//            head.next = null;
//            return node.value;
//        }
        head = node;
        n--;
        return node.value;
    }

    public static void main(String[] args) {
        LinkedQueue queue = new LinkedQueue();
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
        queue.enqueue("d");
        System.out.println(queue.n);
        for (int i=0;i<13;i++){
            System.out.println("chulaile "+queue.dequeue());
        }
    }
}
