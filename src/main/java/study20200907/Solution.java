package study20200907;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 提示：
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 */
public class Solution {
    //堆排序
    public int[] topKFrequent(int[] nums, int k) {
        //第一步根据元素的频次构建一个哈希表
        Map<Integer,Integer> map = new HashMap<>();
        for (int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        Queue<Integer> queue1 = new PriorityQueue<>(Comparator.comparingInt(t -> -map.get(t)));
//        Queue<Pair> queue = new PriorityQueue<>(Comparator.comparingInt(t -> -t.value));
        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
//            queue.add(new Pair(entry.getKey(),entry.getValue()));
            if (queue1.size()<k){
                queue1.add(entry.getKey());
            }else {
                if (entry.getValue()>queue1.peek()){
                    queue1.remove();
                    queue1.add(entry.getKey());
                }
            }
        }
        int[] arr = new int[k];
        for (int i=0;i<queue1.size();i++){
//            arr[i] = queue.poll().key;
            arr[i] = queue1.poll();
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] a = new Solution().topKFrequent(new int[]{1,1,1,2,2,3},2);
        for (int i:a){
            System.out.println(i);
        }
    }
}
class Pair {
    int key;
    int value;
    public Pair(int key,int value){
        this.key = key;
        this.value =value;
    }
}
