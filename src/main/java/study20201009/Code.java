package study20201009;

import java.util.HashSet;
import java.util.Set;

public class Code {
    int a = 1;
    public static void main(String[] args) {
        ListNode head1 = new ListNode(3);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(0);
        ListNode head4 = new ListNode(-4);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head2;
        System.out.println(new Code().hasCycle(head1));
    }

    public boolean hasCycle(ListNode head) {
        int a;
        Set<ListNode> set = new HashSet();
        while(head!=null){
            boolean b = set.add(head);
            if(b){
                head = head.next;
            }else{
                return true;
            }
        }
        return false;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}