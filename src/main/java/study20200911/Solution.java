package study20200911;

/**
 *剑指 Offer 24. 反转链表
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 限制：
 * 0 <= 节点个数 <= 5000
 */
public class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode cur = null,pre = head;
        while (pre!=null){
            ListNode next = pre.next;
            pre.next = cur;
            cur = pre;
            pre = next;
        }
        return cur;
    }
    public ListNode reverseList_v2(ListNode head) {
        ListNode cur = null;
        while (head!=null){
            ListNode next = head.next;
            head.next = cur;
            cur = head;
            head = next;
        }
        return cur;
    }
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        ListNode pre = dummy;
        while (cur!=null){
            if (head.val==val){
                pre.next = cur.next;
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }
    public ListNode deleteNode_v2(ListNode head, int val) {
        //边界条件判断
        if (head == null)
            return head;
        //如果要删除的是头结点，直接返回头结点的下一个结点即可
        if (head.val == val)
            return head.next;
        ListNode cur = head;
        //找到要删除结点的上一个结点
        while (cur.next != null && cur.next.val != val) {
            cur = cur.next;
        }
        //删除结点
        cur.next = cur.next.next;
        return head;
    }
}

/**
 * 单链表
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}