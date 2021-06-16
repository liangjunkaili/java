package letcode;

import java.util.Arrays;

public class LC0610 {
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int coin : coins){
            for (int j=coin;j<=amount;j++){
                dp[j] += dp[j-coin];
            }
        }
        System.out.println(dp[amount]);
        return dp[amount];
    }

    public static int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[max];
        Arrays.fill(dp,max);
        dp[0] = 0;
        for (int i=1;i<=amount;i++){
            for (int coin : coins){
                if (coin<=i){
                    dp[i] = Math.min(dp[i],dp[i-coin] + 1);
                }
            }
        }
        return dp[amount]>amount?-1:dp[amount];
    }

    public static void main(String[] args) {
        change(0,new int[]{1,2,5});
        System.out.println(coinChange(new int[]{1,2,5},11));
    }
}
