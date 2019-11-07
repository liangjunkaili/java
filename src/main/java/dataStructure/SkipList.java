package dataStructure;

import java.util.Random;

/**
 * 跳表的实现
 * 动态数据结构，支持快速的插入、删除、查找
 * 应用场景：redis的zset是用跳表实现的
 * 链表+多级索引
 * 时间复杂度O(logN)，查找、插入、删除都是
 * 空间复杂度O(N)，这里说明一下，此数据结构是空间换时间，但是实际开发中，多余的索引空间基本可以忽略
 * 在插入和删除的过程中，如何保证跳表的平衡，通过随机函数
 */
public class SkipList {
    /**
     * 索引最大层数，包含原始链表那一层。
     */
    private static final int MAX_LEVEL = 16;
    /**
     * 用于生成随机数，避免链表中结点过多，导致性能下降。
     */
    private Random r;

    private int levelCount;
    private Node head;
    public SkipList() {
        r = new Random();
        head = new Node();
    }

    public void insert(int value){
        Node newNode = new Node();
        newNode.value = value;
        newNode.index = 0;
        if (head.next == null){
            head.next = newNode;
        }
    }
    public void delete(int value){

    }
    public void find(int value){

    }
    /**
     * 生成随机数 level = [1, MAX_LEVEL]。
     * 将结点添加到第 0 层到第 level-1 层索引中。
     */
    private int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; i++) {
            if (r.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    public class Node{
        public int value = -1;
        public int index = 0;
        public Node next = null;
        public Node down = null;
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        int level = skipList.randomLevel();
        System.out.println(level);
    }
}
