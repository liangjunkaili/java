package dataStructure;

public class Heap {
    private int[] a; // 数组，从下标1开始存储数据
    private int n;  // 堆可以存储的最大数据个数
    private int count; // 堆中已经存储的数据个数

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    public void insert(int data) {
        if (count >= n) return; // 堆满了
        ++count;
        a[count] = data;
        int i = count;
        while (i/2 > 0 && a[i] > a[i/2]) { // 自下往上堆化
            swap(a, i, i/2); // swap()函数作用：交换下标为i和i/2的两个元素
            i = i/2;
        }
    }

    private void swap(int[] a, int i, int i1) {
        int temp = a[i];
        a[i] = a[i1];
        a[i1] = temp;
    }

    public static void main(String[] args) {
        Heap heap = new Heap(10);
        heap.insert(1);
        heap.insert(5);
        heap.insert(9);
        heap.insert(43);
        heap.insert(2);
        heap.insert(67);
        for (int i = 0; i <heap.a.length ; i++) {
            System.out.print(heap.a[i]+",");
        }
    }
}