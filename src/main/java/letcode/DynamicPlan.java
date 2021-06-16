package letcode;

/**
 * 动态规划练习
 * 适合用来求解最优问题
 * 多阶段决策最优解模型
 * 最优子结构、无后效性、重复子问题
 *
 * 什么样的问题可以用动态规划解决？
 * 解决动态规划问题的一般思考过程是什么样的？
 * 贪心、分治、回溯、动态规划这四种算法思想又有什么区别和联系？
 */
public class DynamicPlan {
    private int maxW = Integer.MIN_VALUE;//求的最大值
    private int[] weight = {2,2,4,6,3};//物品重量
    private int[] value = {3,4,8,9,6};//物品的价值
    private int n = 5;//物品个数
    private int w = 9;//背包承受的最大重量
    public static void main(String[] args) {
        DynamicPlan dynamicPlan = new DynamicPlan();
        System.out.println(dynamicPlan.bagV2(dynamicPlan.weight, dynamicPlan.n, dynamicPlan.w));
        dynamicPlan.taoBao();
        System.out.println(dynamicPlan.minDist(0,0,4,new int[][]{{1,3,5,9},{2,1,3,4},{5,2,6,7},{6,8,4,3}}));
        System.out.println(dynamicPlan.misDistV2(3,3));
    }

    /**
     * 淘宝双十一促销活动，在购物车中的N个商品中选几个，在满减条件的前提下，最大限度的薅羊毛
     */
    public void taoBao(){
        int[] prices = {15,108,77,65,52};
        int n = prices.length;
        int price = 1000;
        boolean[][] states = new boolean[n][price+1];
        states[0][0] = true;
        if (prices[0]<=price){
            states[0][prices[0]] = true;
        }
        for (int i=1;i<n;i++){
            for (int j=0;j<=price;j++){
                if (states[i-1][j])
                    states[i][j] = true;
            }
            for (int j=0;j<=price-prices[i];j++){
                if (states[i-1][j+prices[i]])
                    states[i][j+prices[i]] = true;
            }
        }
        int i;
        for (i=w;i<price;i++){
            if (states[n-1][i])
                break;
        }
        if (i>price)return;
        for (int j=n-1;j>=1;j--){
            if (i-prices[j]>=0&&states[j-1][i-prices[j]]){
                System.out.println(prices[j]+"--");
                i = i-prices[i];
            }
        }
        if (i!=0)
            System.out.println(prices[0]);
    }

    /**
     * 对于一组不同重量、不可分割的物品，需要选择一些装入背包，在满足背包的最大重量限制下，
     * 求背包中物品总重量的最大值？
     */
    public int bag(int[] weight,int n,int w){
        boolean[][] states = new boolean[n][w+1];
        states[0][0] = true;
        if (weight[0]<=w){
            states[0][weight[0]] = true;
        }
        for (int i=1;i<n;i++){
            for (int j=0;j<=w;j++){
                if (states[i-1][j])
                    states[i][j] = true;
            }
            for (int j=0;j<=w-weight[i];j++){
                if (states[i-1][j])
                    states[i][j+weight[i]] = true;
            }
        }
        for (int i=w;i>=0;i--){
            if (states[n-1][i])
                return i;
        }
        return 0;
    }

    /**
     * 降低空间的消耗，采用一维数组
     * @param items
     * @param n
     * @param w
     * @return
     */
    public int bagV2(int[] items,int n,int w){
        boolean[] states = new boolean[w+1];
        states[0] = true;
        if (items[0]<=w){
            states[items[0]] = true;
        }
        for (int i=1;i<n;i++){
            for (int j=w-weight[i];j>=0;j--){
                if (states[j])
                    states[j+items[i]] = true;
            }
        }
        for (int i=w;i>=0;i--){
            if (states[i])
                return i;
        }
        return 0;
    }

    /**
     * 在满足最大重量的限制下，求最大价值
     * @param weight
     * @param value
     * @param n
     * @param w
     * @return
     */
    public int bagV3(int[] weight,int[] value,int n,int w){
        int[][] states = new int[n][w+1];
        for (int i=0;i<n;i++){
            for (int j=0;j<=w;j++){
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (weight[0]<=w){
            states[0][weight[0]] = value[0];
        }
        for (int i=1;i<n;i++){
            for (int j=0;j<=w;j++){
                if (states[i-1][j]>=0)
                    states[i][j] = states[i-1][j];
            }
            for (int j=0;j<=w-weight[i];j++){
                if (states[i-1][j]>=0){
                    int v = states[i-1][j]+value[i];
                    if (v>states[i][j+weight[i]]){
                        states[i][j+weight[i]] = v;
                    }
                }
            }
        }
        int maxValue = -1;
        for (int i=0;i<=w;i++){
            if (states[n-1][i]>maxValue)
                maxValue = states[n-1][i];
        }
        return maxValue;
    }

    /**
     * 在矩阵中求从左上角到右下角的最短距离，只能向下或向右移动
     * 状态转移表法
     */
    public int minDist(int i,int j,int n,int[][] req){
        int[][] res = new int[n][n];
        res[0][0] = req[0][0];
        for (int i1=1;i1<n;i1++){
            res[i1][0] = req[i1][0]+res[i1-1][0];
        }
        for (int j1=1;j1<n;j1++){
            res[0][j1] = req[0][j1]+res[0][j1-1];
        }
        for (int i1=1;i1<n;i1++){
            for (int j1=1;j1<n;j1++){
                res[i1][j1] = req[i1][j1]+min(res[i1-1][j1],res[i1][j1-1]);
            }
        }
        return res[n-1][n-1];
    }

    /**
     * 状态转移方程法
     */
    private int[][] req = {{1,3,5,9},{2,1,3,4},{5,2,6,7},{6,8,4,3}};
    private int[][] mem = new int[4][4];
    public int misDistV2(int i,int j){
        if (i==0&&j==0)
            return req[0][0];
        if (mem[i][j]>0)
            return mem[i][j];
        int minLeft = Integer.MAX_VALUE;
        if (j-1>=0){
            minLeft = misDistV2(i,j-1);
        }
        int minUp = Integer.MAX_VALUE;
        if (i-1>=0){
            minUp = misDistV2(i-1,j);
        }
        int cur = req[i][j] + min(minLeft,minUp);
        mem[i][j] = cur;
        return cur;
    }
    private int min(int a,int b){
        if (a>b)
            return b;
        return a;
    }

    /**
     * 硬币找零问题，假设有几种不同币值的硬币v1、v2....vn，要支付w元，最少需要多少个硬币？
     */
    private int minCoin = Integer.MAX_VALUE;
    private int[] coins = {1,3,5};
    private int ret = 9;
    public void coin(int i,int cur){

    }
}
