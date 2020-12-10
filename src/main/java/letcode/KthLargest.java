package letcode;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class KthLargest {
    private PriorityQueue<Integer> bigQueue;
    private PriorityQueue<Integer> smallQueue;
    /** initialize your data structure here. */
    public KthLargest() {
        bigQueue = new PriorityQueue<>(((o1, o2) -> o2-o1));
        smallQueue = new PriorityQueue<>();
    }
    public ListNode reverseList(ListNode head) {
//        ListNode pre= null;
//        ListNode cur = head;
//        while (cur!=null){
//            ListNode next = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = next;
//        }
//        return pre;
        if (head==null||head.next==null){
            return head;
        }
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
    public void addNum(int num) {
        bigQueue.add(num);
        smallQueue.add(bigQueue.poll());
        if (smallQueue.size()>bigQueue.size()){
            bigQueue.add(smallQueue.poll());
        }
    }

    public double findMedian() {
        return bigQueue.size()>smallQueue.size()?bigQueue.peek():
                (bigQueue.peek()+smallQueue.peek())*0.5;
    }
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(((o1, o2) -> o1.val-o2.val));
        for (ListNode listNode:lists){
            while (listNode!=null){
                queue.add(listNode);
                listNode = listNode.next;
            }
        }
        ListNode node = new ListNode(0);
        ListNode cur = node;
        while (!queue.isEmpty()){
            cur.next = queue.poll();
            cur = cur.next;
        }
        return node.next;
    }
    public ListNode deleteDuplicates(ListNode head) {
        Set<Integer> set = new HashSet<>();
        ListNode cur = head;
        ListNode pre = head;
        while (cur!=null){
            if (set.contains(cur.val)){
                pre.next = cur.next;
            }else {
                set.add(cur.val);
            }
            cur = cur.next;
        }
        return head;
    }
    public ListNode middleNode(ListNode head) {
        ListNode low = head,hi = head;
        while (low!=null&&hi!=null){
            low = low.next;
            hi = hi.next.next;
        }
        return low;
    }
    public boolean isPalindrome(ListNode head) {
        ListNode lo = head,hi = head;
        while (hi!=null&&hi.next!=null){
            lo = lo.next;
            hi = hi.next.next;
        }
        ListNode a = lo;
        ListNode pre = null;
        while (a!=null){
            ListNode next = a.next;
            a.next = pre;
            pre = a;
            a = next;
        }
        while (a!=null){
            if (a.val!=head.val){
                return false;
            }
            a =a.next;
            head = head.next;
        }
        return true;
    }
    public ListNode swapPairs(ListNode head) {
        ListNode pre = new ListNode(-1);
        ListNode temp = head;
        ListNode cur = pre;
        while (temp!=null&&temp.next!=null){
            ListNode next = temp.next;
            cur.next = next;
            temp.next = next.next;
            next.next = temp;
            temp = temp.next;
            cur = cur.next.next;
        }
        return pre.next;
    }
    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(2);
        ListNode node8 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        System.out.println(LocalDate.now().toString());
//        kthLargest.swapPairs(node1);
//        node4.next = node5;
//        node5.next = node6;
//        kthLargest.middleNode(node1);
//        kthLargest.deleteDuplicates(node1);
//        node7.next = node8;
//        kthLargest.mergeKLists(new ListNode[]{node1});
//        kthLargest.addNum(1);
//        kthLargest.addNum(2);
//        kthLargest.findMedian();
//        kthLargest.addNum(3);
//        kthLargest.findMedian();
//        kthLargest.add(-1);
//        kthLargest.add(1);
//        kthLargest.add(-2);
//        kthLargest.add(-4);
//        kthLargest.add(3);
//        lastStoneWeight(new int[]{2,7,4,1,8,1});
//        getLeastNumbers(new int[]{0,0,0,2,0,5},0);
//        kClosest(new int[][]{{-5,4},{-6,-5},{4,6}},2);
    }
    public static int[][] kClosest(int[][] points, int K) {
        if (K==0){
            return new int[][]{};
        }
        int[][] ret = new int[K][2];
        PriorityQueue<int[]> queue =
                new PriorityQueue<>(K,((o1, o2) -> (o2[0]*o2[0]+o2[1]*o2[1])-(o1[0]*o1[0]+o1[1]*o1[1])));
        for (int i=0;i<K;i++){
            queue.add(points[i]);
        }
        for (int i=K;i<points.length;i++){
            int[] top = queue.peek();
            int sum = top[0]*top[0]+top[1]*top[1];
            if (points[i][0]*points[i][0]+points[i][1]*points[i][1]<sum){
                queue.poll();
                queue.add(points[i]);
            }
        }
        int j=0;
        while (!queue.isEmpty()){
            ret[j] = queue.poll();
            j++;
        }
        return ret;
    }
    public static int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k,((o1, o2) -> o2-o1));
        for (int i=0;i<k;i++){
            queue.add(arr[i]);
        }
        for (int i=k;i<arr.length;i++){
            int top = queue.peek();
            if (arr[i]<top){
                queue.poll();
                queue.add(arr[i]);
            }
        }
        int[] ret = new int[queue.size()];
        int index=0;
        while (!queue.isEmpty()){
            ret[index] = queue.poll();
            index++;
        }
        return ret;
    }
    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(((o1, o2) -> o2-o1));
        for (int i:stones){
            queue.add(i);
        }
        while (queue.size()>1){
            int y = queue.poll();
            int x = queue.poll();
            if (y-x>0){
                queue.add(y-x);
            }
        }
        return queue.isEmpty()?0:queue.poll();
    }
    private int size;
    private int count;
    private int[] arr;
    public KthLargest(int k, int[] nums) {
        arr = new int[k];
        size = k;
        count=0;
        for (Integer integer:nums)
            add(integer);
    }

    public int add(int val) {
        if (count<size){
            if (count==0){
                arr[0] = val;
            }else {
                arr[count] = val;
                int temp = count;
                while (temp>0){
                    int parent = temp/2;
                    if (arr[parent]>arr[temp]){
                        swap(arr,parent,temp);
                    }
                    temp = parent;
                }
            }
            count++;
        }else {
            if (val>arr[0]){
                arr[0] = val;
                heapify(arr,size,0);
            }
        }
        return arr[0];
    }
    private void heapify(int[] arr,int n,int i){
        while (true){
            int pos = i;
            if (i*2+1<n&&arr[i]>arr[i*2+1])
                pos = i*2+1;
            if ((i*2+2<n&&arr[pos]>arr[i*2+2]))
                pos = i*2+2;
            if (pos==i)
                break;
            swap(arr,i,pos);
            i = pos;
        }
    }
    private void swap(int[] arr,int i,int i1){
        int temp = arr[i];
        arr[i] = arr[i1];
        arr[i1] = temp;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}