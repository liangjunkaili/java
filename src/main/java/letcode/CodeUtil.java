package letcode;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class CodeUtil {

    //127 ms-40 MB
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<nums.length;j++){
                if(nums[i]+nums[j]==target&&i!=j){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    //3 ms-39.9 MB
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            int j = target - nums[i];
            if (map.containsKey(j)){
                return new int[]{map.get(j),i};
            }
            map.put(nums[i],i);
        }
        return new int[]{};
    }
    public static int reverse(int x) {
        if (x>Integer.MAX_VALUE || x<Integer.MIN_VALUE){
            return 0;
        }
        int b = x;
        if (x<0){
            b*=-1;
        }
        String s = Integer.toString(b);
        StringBuilder sb = new StringBuilder(s);
        String y = sb.reverse().toString();
        long m = Long.valueOf(y);
        if (m>Integer.MAX_VALUE || m<Integer.MIN_VALUE){
            return 0;
        }
        if (x<0){
            m*=-1;
        }
        return (int) m;
    }
    public static int reverse2(int x){
        long n = 0;
        while(x != 0) {
            n = n*10 + x%10;
            x = x/10;
        }
        if (n>Integer.MAX_VALUE || n<Integer.MIN_VALUE){
            return 0;
        }
        return (int)n;
//        return (int)n==n? (int)n:0;
    }
    public boolean isPalindrome(int x) {
        String s = Integer.toString(x);
        StringBuilder sb = new StringBuilder(s);
        String b = sb.reverse().toString();
        return s.equals(b);
    }
    public int numJewelsInStones(String J, String S) {
        int count = 0;
        char[] chars = S.toCharArray();
        for (int i=0;i<chars.length;i++){
            char c = chars[i];
            if (J.indexOf(c)!=-1){
                count++;
            }
        }
        return count;
    }
    public int minArray(int[] numbers) {
        int min = numbers[0];
        for (int i=0;i<numbers.length;i++){
            if (min>numbers[i]){
                min = numbers[i];
            }
        }
        return min;
    }
//    public double average(int[] salary) {
//        int max ,min = salary[0];
//        for (int i=0;i<salary.length;i++){
//            if (min>salary[i]){
//
//            }
//        }
//    }
    public static void main(String[] args) {
        int[] nums = {3,3};
//        System.out.println(twoSum(nums,6));
        System.out.println(reverse2(123));
    }
}
