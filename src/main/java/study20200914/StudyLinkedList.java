package study20200914;

/**
 * 链表：
 *  单链表
 *  双链表
 *  循环链表
 * 节点
 */
public class StudyLinkedList {
    public static void main(String[] args) {
        StudyLinkedList list = new StudyLinkedList();
//        Node root = new Node(4);
//        Node node5 = new Node(5);
//        Node node1 = new Node(1);
//        Node node9 = new Node(9);
//        root.next = node5;
//        node5.next = node1;
//        node1.next = node9;
//        Node<Integer> node = list.delete(root,5);
//        list.search(node);
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        list.mergeTwoLists(l1,l2);
    }
    public void search(Node<Integer> head){
        while (head!=null){
            System.out.println(head.t);
            head = head.next;
        }
    }
    public Node<Integer> delete(Node<Integer> head,int val){
        Node<Integer> dummy = new Node<>(0);//哨兵节点
        dummy.next = head;
        Node<Integer> pre = dummy;
        Node<Integer> cur = head;
        while (cur!=null){
            if (cur.t ==val){
                //执行删除操作
                pre.next = cur.next;
                break;
            }else {
                pre = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode a = new ListNode(-1);
        ListNode result = a;
        while (l1!=null&&l2!=null){
            if (l1.val<l2.val){
                result.next = l1;
                l1 = l1.next;
            }else {
                result.next = l2;
                l2 = l2.next;
            }
            result = result.next;
        }
        result.next = l1==null?l2:l1;
        return a.next;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Node<T>{
    T t;
    Node<T> next;
    public Node(T t){
        this.t = t;
    }
}
class DoubleNode<T> extends Node<T>{
    Node<T> pre;
    public DoubleNode(T t) {
        super(t);
    }
}