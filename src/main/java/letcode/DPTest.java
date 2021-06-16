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
 */
public class DPTest {
    public static void main(String[] args) {
//        long begin = System.nanoTime();
//        System.out.println(bag(new int[]{2,5,4,6,8},11));
//        System.out.println(System.nanoTime() - begin);
//        begin = System.nanoTime();
//        System.out.println(bagV2(new int[]{2,5,4,6,8},11));
//        System.out.println(System.nanoTime() - begin);
//        bagV3(new int[]{2,5,4,6,8},new int[]{2,3,4,5,6},11);
        System.out.println(coin(new int[]{2,5,1},2));
        System.out.println(coinV2(new int[]{2,5,1},2));
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
