package letcode;

/**
 * 动态规划练习
 * 适合用来求解最优问题
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
}
