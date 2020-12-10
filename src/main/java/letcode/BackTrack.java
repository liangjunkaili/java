package letcode;

/**
 * 回溯算法
 */
public class BackTrack {
    private int maxW = Integer.MIN_VALUE;//求的最大值
    private int maxV = Integer.MIN_VALUE;//求的最大值
    private int[] weight = {2,2,4,6,3};//物品重量
    private int[] value = {3,4,8,9,6};//物品的价值
    private int n = 5;//物品个数
    private int w = 9;//背包承受的最大重量
    private boolean[][] mem = new boolean[5][10];
    public static void main(String[] args) {
        BackTrack backTrack = new BackTrack();
        backTrack.bagV2(0,0);
        System.out.println(backTrack.maxW);
        backTrack.bagV3(0,0,0);
        System.out.println(backTrack.maxV);
    }
    /**
     * 对于一组不同重量、不可分割的物品，需要选择一些装入背包，在满足背包的最大重量限制下，
     * 求背包中物品总重量的最大值？
     * i：表示第几个物品
     * cw：表示当前的重量
     */
    public void bag(int i,int cw){
        if (cw==w||i==n){
            if (cw>maxW){
                maxW = cw;
            }
            return;
        }
        bag(i+1,cw);
        if (cw+weight[i]<=w){
            bag(i+1,cw+weight[i]);
        }
    }

    /**
     * 备忘录优化
     */
    public void bagV2(int i,int cw){
        if (cw>=w||i==n){
            if (cw>maxW){
                maxW = cw;
            }
            return;
        }
        if (mem[i][cw]){
            return;
        }
        mem[i][cw] = true;
        bag(i+1,cw);
        if (cw+weight[i]<=w){
            bag(i+1,cw+weight[i]);
        }
    }

    /**
     * 求最大的价值，在满足最大重量限制下
     * @param i
     * @param cw
     * @param cv
     */
    public void bagV3(int i,int cw,int cv){
        if (i==n||cw==w){
            if (cv>=maxV){
                maxV = cv;
            }
            return;
        }
        bagV3(i+1,cw,cv);
        if (cw+weight[i]<=w){
            bagV3(i+1,cw+weight[i],cv+value[i]);
        }
    }
}
