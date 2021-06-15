package letcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution2021615 {
    public static void main(String[] args) {
        Solution2021615 solution2021615 = new Solution2021615();
        System.out.println(solution2021615.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solution2021615.lengthOfLongestSubstringV2("dvdf"));
    }
    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 例子："abcabcbb" n = 8,
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length()<=0){
            return 0;
        }
        if (s.length()==1){
            return 1;
        }
        for (int l=s.length();l>0;l--){
            for (int i=0;i<s.length();i++){
                int j = l+i-1;
                if (j>s.length()-1){
                    break;
                }
                if (hasContains(s.substring(i,j+1))){
                    return s.substring(i,j+1).length();
                }
            }
        }
        return -1;
    }
    //返回TRUE表示没有重复的字符
    private boolean hasContains(String s){
        HashSet<Character> set = new HashSet<>();
        for (Character c : s.toCharArray()){
            set.add(c);
        }
        return set.size()==s.length();
    }

    /**
     * 时间窗口
     * 例子："abcabcbb" n = 8
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringV2(String s) {
        int left = 0,right = 0,n = s.length(),ans = 0;
        Map<Character,Integer> map = new HashMap<>();
        for (;right<n;right++){
            if (map.containsKey(s.charAt(right))){
                left = Math.max(map.get(s.charAt(right))+1,left);
            }
            map.put(s.charAt(right),right);
            ans = Math.max(ans,right-left+1);
        }
        return ans;
    }
}
