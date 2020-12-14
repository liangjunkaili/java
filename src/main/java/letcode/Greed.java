package letcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 贪心算法
 * 经典应用：霍夫曼编码、Prim和Kruskal最小生成树算法、Dijkstra单源最短路径算法
 * 霍夫曼编码：考察文本中有多少个不同的字符以及每个字符出现的频率，把出现频率比较多的字符，
 * 用稍微短一些的编码，出现频率比较少的字符，用稍微长一些的编码
 * 将每个字符看做一个节点，按频率放到优先级队列中，从队列中取出最小的两个节点，然后重新构建
 * 一个新的节点作为这两个节点的父节点，最后放入队列，一直重复到队列没有数据
 */
public class Greed {
    public static void main(String[] args) {
        Greed greed = new Greed();
        System.out.println(greed.removeKdigits("10200",1));
    }
    /**
     * m个糖果（s1,s2,....sm）分给n个孩子（g1,g2,.....gn），m<n
     */
    public void divideCandy(){

    }

    /**
     * 假设有1元、2元、5元、10元、等，对应的张数为c1,c2,c5,c10等，支付K元，最少用多少张纸币？
     */
    public void coinDispenser(){

    }

    /**
     * n个区间，分别为[l1,r1],[l2,r2]...[ln,rn]，选出两两不相交的区间，最多能选多少个？
     * 思路：假设n个区间最左端点是lmin，最右端点是rmax，按照起始端点从小到大排序，
     * 每次选择时，左端点跟前面已经覆盖的区间不重合，右端点又小的
     * 同类问题：任务调度、教师排课
     */
    public void sectionCover(){

    }

    /**
     * 在一个非负整数a中，从中移除k个数字，让剩下的数字值最小，如何选择移除哪k个数字呢?
     */
    public String removeKdigits(String num, int k) {
        Deque<Character> deque = new LinkedList<>();
        char[] chars = num.toCharArray();
        for (int i=0;i<chars.length;i++){
            char c1 = chars[i];
            while (!deque.isEmpty()&&k>0&&deque.peekLast()>c1){
                deque.pollLast();
                k--;
            }
            deque.offerLast(c1);
        }
        for (int i=0;i<k;i++){
            deque.pollLast();
        }
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()){
            char c = deque.pollFirst();
            if (c=='0'&&leadingZero){
                continue;
            }
            leadingZero = false;
            sb.append(c);
        }
        return sb.length()==0?"0":sb.toString();
    }

    /**
     * 假设有n个人等待被服务，但是服务窗口只有一个，每个人需要被服务的时间长度是不同的，如何
     * 安排被服务的先后顺序，才能让这n个人总的等待时间最短呢？
     */
    public void test2(){

    }
}
