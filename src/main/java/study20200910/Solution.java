package study20200910;

/**
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
 * 统计一个数字在排序数组中出现的次数。
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 * 限制：
 * 0 <= 数组长度 <= 50000
 */
public class Solution {
    //1、遍历数组；
    public int search(int[] nums, int target) {
        int count = 0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]==target)
                count++;
        }
        return count;
    }
    //2、二分查找？
    public int search_v2(int[] nums, int target) {
        return binary(nums,0,nums.length-1,target)-binary(nums,0,nums.length-1,target-1);
    }
    //找到右边界
    private int binary(int[] nums,int start,int end,int target){
        while (start<=end){
            int m = (start+end)/2;
            if (nums[m]>target){
                end = m-1;
            }
            else if (nums[m]<=target){
                start = m+1;
            }
        }
        return end;
    }
    public static void main(String[] args) {
        System.out.println(new Solution().search_v2(new int[]{5,7,7,8,8,10},8));
    }
}
