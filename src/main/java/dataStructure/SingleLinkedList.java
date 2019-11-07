package dataStructure;

/**
 * 单链表
 */
public class SingleLinkedList {

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        Node head = singleLinkedList.head;
        singleLinkedList.insert(1);
        singleLinkedList.insert(2);
        singleLinkedList.insert(3);
        singleLinkedList.insert(4);
//        singleLinkedList.delete(1);
        singleLinkedList.lru(5);
        singleLinkedList.lru(2);
        System.out.println(singleLinkedList.size);
        Node cur = head.next;
        while (cur!=null){
            System.out.println(cur.value+"==");
            cur = cur.next;
        }
    }
    private Node head;
    private Node tail;
    private int size = 1;

    public SingleLinkedList() {
        head = new Node(-1);
        tail = head;
    }

    public void lru(int value){
        Node node = find(value);
        if (node!=null){
            delete(value);
            insert(value);
        }else {
            if (size>5){
                delete(tail.value);
                insert(value);
            }else {
                insert(value);
            }
        }
    }
    public void insert(int value){
        Node newNode = new Node(value);
        if (head.next==null){
            tail = newNode;
        }
        newNode.next = head.next;
        head.next = newNode;
        size++;
    }
    public void delete(int value){
        Node node = find(value);
        if (node==null){
            return;
        }
        Node next = node.next;
        if (next!=null){
            node.value = next.value;
            node.next = next.next;
        }else {
            Node pre = head;
            while (pre.next!=null){
                if (pre.next==node){
                    pre.next = null;
                    return;
                }
                pre = pre.next;
            }
        }
    }
    public Node find(int value){
        Node cur = head.next;
        while (cur!=null){
            if (cur.value==value){
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    public boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        //快慢指针找到链表的中点
        Node fast = head.next.next;
        Node slow = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //翻转链表前半部分
        Node pre = null;
        Node next = null;
        while (head != slow) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        //如果是奇数个节点，去掉后半部分的第一个节点。

        if (fast != null) {
            slow = slow.next;
        }
        //回文校验
        while (pre != null) {
            if (pre.value != slow.value) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }

        return true;

    }
    public class Node{
        private int value;
        private Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(int value) {
            this(value,null);
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
