package letcode;

import java.util.Arrays;

/**
 * 动态规划问题练习
 * 1、背包问题
 * 对于一组不同重量、不可分割的物品，我们需要选择一些装入背包，在满足背包最大重量限制的前提下，背包中物品总重量的最大值是多少呢？
 * 对于一组不同重量、不同价值、不可分割的物品，我们选择将某些物品装入背包，在满足背包最大重量限制的前提下，背包中可装入物品的总价值最大是多少呢？
 * 2、硬币找零问题
 * 假设我们有几种不同币值的硬币 v1，v2，……，vn（单位是元）。如果我们要支付 w 元，求最少需要多少个硬币。
 * f(w) = 1 + min(f(w-v1),f(w-v2),......,f(w-vn));f(v1) = 1,f(v2) = 1,......,f(vn) = 1;
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 3、最长回文串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 4、不同路径问题
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 *
 * 进阶---增加障碍物
 *
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * dist(i,j) = grid[i][j] + min(dist(i-1,j),dist(i,j-1))
 * 5、最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 */
public class DPTest {
    public static void main(String[] args) {
        DPTest dpTest = new DPTest();
//        long begin = System.nanoTime();
//        System.out.println(bag(new int[]{2,5,4,6,8},11));
//        System.out.println(System.nanoTime() - begin);
//        begin = System.nanoTime();
//        System.out.println(bagV2(new int[]{2,5,4,6,8},11));
//        System.out.println(System.nanoTime() - begin);
//        bagV3(new int[]{2,5,4,6,8},new int[]{2,3,4,5,6},11);
        System.out.println(coin(new int[]{2,5,1},2));
        System.out.println(coinV2(new int[]{2,5,1},2));
        System.out.println(dpTest.longestPalindrome("cbbd"));
        System.out.println(dpTest.lengthOfLIS(new int[]{4,10,4,3,8,9}));
        System.out.println(dpTest.numTrees(5));
    }

    /**
     * G(n) 表示满足的总数
     * F(i) 表示以i为根的组合数，F(i) = G(i-1)*G(n-i)
     * G(n) = F(1) + F(2) + ...... + F(n)
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] g = new int[n+1];
        g[0] = 1;
        g[1] = 1;
        for (int i=2;i<=n;i++){
            for (int j=1;j<=i;j++){
                g[i] += g[j-1] * g[i-j];
            }
        }
        return g[n];
    }
    public int numDecodings(String s) {
        int n = s.length();
        int[] states = new int[n+1];
        states[0] = 1;
        for (int i=1;i<=n;i++){
            if (s.charAt(i-1)!='0'){
                states[i] += states[i-1];
            }
            if (i>1 && s.charAt(i-2)!='0' && ((s.charAt(i-2)-'0')*10 + (s.charAt(i-1)-'0')<=26)){
                states[i] += states[i-2];
            }
        }
        return states[n];
    }
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] length = new int[n];
        length[0] = 1;
        for (int i=1;i<n;i++){
            length[i] = 1;
            for (int j=0;j<i;j++){
                if (nums[i]>nums[j]){
                    length[i] = Math.max(length[i],length[j]+1);
                }
            }
        }
        int max = -1;
        for (int i : length){
            max = Math.max(max,i);
        }
        return max;
    }
    public int minPathSum(int[][] grid) {
        int m = grid.length,n = grid[0].length;
        int[][] dist = new int[m][n];
        dist[0][0] = grid[0][0];
        for (int i=1;i<m;i++){
            dist[i][0] = dist[i-1][0] + grid[i][0];
        }
        for (int i=1;i<n;i++){
            dist[0][i] = dist[0][i-1] + grid[0][i];
        }
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                dist[i][j] = grid[i][j] + Math.min(dist[i-1][j],dist[i][j-1]);
            }
        }
        return dist[m-1][n-1];
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length,n = obstacleGrid[0].length;
        int[][] dist = new int[m][n];
        for (int i=0;i<m;i++){
            if (obstacleGrid[i][0]==1){
                dist[i][0] = 0;
                break;
            }else {
                dist[i][0] = 1;
            }
        }
        for (int i=0;i<n;i++){
            if (obstacleGrid[0][i]==1){
                dist[0][i] = 0;
                break;
            }else {
                dist[0][i] = 1;
            }
        }
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                if (obstacleGrid[i][j]!=1){
                    dist[i][j] = dist[i-1][j] + dist[i][j-1];
                }
            }
        }
        return dist[m-1][n-1];
    }
    /**
     * dist[m][n] = dist[m-1][n] + dist[m][n-1]
     * @param m >= 1
     * @param n <= 100
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dist = new int[m][n];
        for (int i=0;i<m;i++){
            dist[i][0] = 1;
        }
        for (int i=0;i<n;i++){
            dist[0][i] = 1;
        }
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                dist[i][j] = dist[i-1][j] + dist[i][j-1];
            }
        }
        return dist[m-1][n-1];
    }
    /**
     * P(i,j) = P(i+1,j-1) && charAt(i) == charAt(j)
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int length = s.length();
        if (length==1){
            return s;
        }
        int begin=0,max=1;
        boolean[][] states = new boolean[length][length];
        for (int i=0;i<length;i++){
            states[i][i] = true;
        }
        for (int l=2;l<=length;l++){
            for (int i=0;i<length;i++){
                int j = l+i-1;
                if (j>=length){
                    break;
                }
                if (s.charAt(i)!=s.charAt(j)){
                    states[i][j] = false;
                }else {
                    if (j-i<3){
                        states[i][j] = true;
                    }else {
                        states[i][j] = states[i+1][j-1];
                    }
                }
                if (states[i][j] && j-i+1>max){
                    begin = i;
                    max = j-i+1;
                }
            }
        }
        return s.substring(begin,max+begin);
    }
    public static int coin(int[] coins,int w){
        for (int coin : coins){
            if (w==coin)
                return 1;
        }
        int[] numbers = new int[w+1];
        Arrays.fill(numbers,w+1);
        numbers[0] = 0;
        for (int i=1;i<=w;i++){
            for (int coin : coins){
                if (coin<=i){
                    numbers[i] = Math.min(numbers[i],numbers[i-coin] + 1);
                }
            }
        }
        return numbers[w] > w ? -1 : numbers[w];
    }
    public static int coinV2(int[] coins,int w){
        int[] numbers = new int[w+1];
        numbers[0] = 1;
        for (int coin : coins){
            for (int i=coin;i<=w;i++){
                numbers[i] += numbers[i-coin];
            }
        }
        return numbers[w];
    }
    public static int bag(int[] items,int w){
        int n = items.length;
        boolean[][] states = new boolean[n][w+1];
        states[0][0] = true;
        if (items[0]<=w){
            states[0][items[0]] = true;
        }
        for (int i=1;i<n;i++){
            for (int j=0;j<=w;j++){
                if (states[i-1][j]){
                    states[i][j] = true;
                }
            }
            for (int j=0;j<=w-items[i];j++){
                if (states[i-1][j]){
                    states[i][j+items[i]] = true;
                }
            }
        }
        for (int i=w;i>=0;i--){
            if (states[n-1][i]){
                return i;
            }
        }
        return -1;
    }
    public static int bagV2(int[] items,int w){
        int n = items.length;
        boolean[] states = new boolean[w+1];
        states[0] = true;
        if (items[0]<=w){
            states[items[0]] = true;
        }
        for (int i=1;i<n;i++){
            for (int j=w-items[i];j>=0;j--){
                if (states[j]){
                    states[j+items[i]] = true;
                }
            }
        }
        for (int i=w;i>=0;i--){
            if (states[i]){
                return i;
            }
        }
        return -1;
    }
    public static void bagV3(int items[],int values[],int w){
        int n = items.length;
        int[][] states = new int[n][w+1];
        for (int i=0;i<n;i++){
            for (int j=0;j<w+1;j++){
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (items[0]<=w){
            states[0][items[0]] = values[0];
        }
        for (int i=1;i<n;i++){
            for (int j=0;j<=w;j++){
                if (states[i-1][j]>0){
                    states[i][j] = states[i-1][j];
                }
            }
            for (int j=0;j<=w-items[i];j++){
                if (states[i-1][j]>0){
                    states[i][j+items[i]] = states[i-1][j] + values[i];
                }
            }
        }
        for (int i=w;i>=0;i--){
            if (states[n-1][i]>0){
                System.out.println(states[n-1][i]);
                return;
            }
        }
    }
}
