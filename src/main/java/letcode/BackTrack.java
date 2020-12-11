package letcode;

/**
 * 回溯算法的应用：
 *  深度优先搜索算法
 *  正则表达式匹配
 *  编译原理中的语法分析
 *  数独
 *  八皇后
 *  0-1背包
 *  图的着色
 *  旅行商问题
 *  全排列
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
        backTrack.minDist(0,0,new int[][]{{1,3,5,9},{2,1,3,4},{5,2,6,7},{6,8,4,3}},4,0);
        System.out.println(backTrack.minDist);
        backTrack.cal8Queens(0);
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
    private int minDist = Integer.MAX_VALUE;
    public void minDist(int i,int j,int[][] req,int n,int dist){
        dist += req[i][j];
        if (i==n-1&&j==n-1){
            if (dist<minDist)
                minDist = dist;
            return;
        }
        if (i<n-1){
            minDist(i+1,j,req,n,dist);
        }
        if (j<n-1){
            minDist(i,j+1,req,n,dist);
        }
    }
    /**
     * 硬币找零问题，假设有几种不同币值的硬币v1、v2....vn，要支付w元，最少需要多少个硬币？
     */
    private int minCoin = Integer.MAX_VALUE;
    private int[] coins = {1,3,5};
    private int ret = 9;
    public void coin(int[] coins,int ret){
        int level = ret/coins[0];
    }
    private int[] result = new int[8];//下标代表行，值代表列
    public void cal8Queens(int row){
        if (row==8){
            printQueen(result);
            return;
        }
        for (int column=0;column<8;column++){
            if (isOk(row,column)){
                result[row] = column;
                cal8Queens(row+1);
            }
        }
    }

    private boolean isOk(int row, int column) {
//        int leftUp = column-1,rightUp = column+1;
        for (int i=row-1;i>=0;i--){
            if (result[i]==column)//判断第i行的column列是否有棋子
                return false;
//            if (leftUp>=0){
//                if (result[i]==leftUp)
//                    return false;
//            }
//            if (rightUp<8){
//                if (result[i]==rightUp)
//                    return false;
//            }
            //判断是否在一条对角线的方式
            if (Math.abs(row-i)==Math.abs(column-result[i])){
                return false;
            }
//            leftUp--;
//            rightUp++;
        }
        return true;
    }
    public void printQueen(int[] queens){
        for (int row=0;row<8;row++){
            for (int column=0;column<8;column++){
                if (queens[row]==column)
                    System.out.print("Q");
                else
                    System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 正则表达式匹配
     */
    private boolean matched = false;
    private char[] pattern = new char[]{};
    private int len = 10;
    public boolean pattern(char[] text,int l){
        rmatch(0,0,text,l);
        return matched;
    }

    private void rmatch(int i, int i1, char[] text, int l) {
        if (matched)
            return;
        if (i1==len){
            if (i==l){
                matched = true;
            }
            return;
        }
        if (pattern[i1] == '*'){
            for (int k=0;k<=l-i;k++){
                rmatch(i+k,i1+1,text,l);
            }
        }else if (pattern[i1]=='?'){
            rmatch(i,i1+1,text,l);
            rmatch(i+1,i1+1,text,l);
        }else if (i<l&&pattern[i1]==text[i]){
            rmatch(i+1,i1+1,text,l);
        }
    }
}
