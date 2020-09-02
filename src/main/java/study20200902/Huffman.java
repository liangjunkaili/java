package study20200902;


import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Huffman树-最优二叉树
 * 定义：给定n个权值作为n个叶子结点，构造一棵二叉树，若树的带权路径长度达到最小，则这棵树被称为哈夫曼树。
 * 如何构造Huffman树：
 * 1. 将w1、w2、…，wn看成是有n 棵树的森林(每棵树仅有一个结点)；
 * 2. 在森林中选出根结点的权值最小的两棵树进行合并，作为一棵新树的左、右子树，且新树的根结点权值为其左、右子树根结点权值之和；
 * 3. 从森林中删除选取的两棵树，并将新树加入森林；
 * 4. 重复(02)、(03)步，直到森林中只剩一棵树为止，该树即为所求得的哈夫曼树。
 */
public class Huffman {
    private HuffmanNode root;

    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue();
        queue.add(10);
        queue.add(5);
        queue.add(2);
        queue.add(7);
        queue.add(18);
        queue.add(21);
        queue.add(34);
        while (queue.size()>1){
            int i = queue.poll();
            int j = queue.poll();
            System.out.println(i+"+"+j+"="+(i+j));
            HuffmanNode l = new HuffmanNode(i,null,null);
            HuffmanNode r = new HuffmanNode(j,null,null);
            HuffmanNode p = new HuffmanNode(i+j,l,r);
            queue.add(i+j);
        }
        System.out.println(queue.poll());
    }
}
class HuffmanNode implements Comparable<HuffmanNode>{
    private int key;//权值
    private HuffmanNode left;
    private HuffmanNode right;
    private HuffmanNode parent;

    public HuffmanNode(int key, HuffmanNode left, HuffmanNode right, HuffmanNode parent) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
    public HuffmanNode(int key, HuffmanNode left, HuffmanNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return key-o.key;
    }
}