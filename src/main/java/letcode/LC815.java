package letcode;

import java.util.*;

/**
 * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
 *
 * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
 * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
 *
 * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
 *
 * 提示：
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 105
 * routes[i] 中的所有值 互不相同
 * sum(routes[i].length) <= 105
 * 0 <= routes[i][j] < 106
 * 0 <= source, target < 106
 *
 * 解题思路：
 * 1、用一个map保存每个车站所在的公交线路
 * 2、用一个二维数组保存两条公交线路之间是否有交集
 * 3、用一个一维数组保存所有的从source到target的路线（乘坐的公交车数量）
 * 4、用一个队列保存从source开始经过的车站
 * 5、从所有的线路中找到最小的
 *
 * 思路2：
 * 1、用一个列表保存含有相同车站的路线
 * 2、分别用set保存包含source和target的路线
 * 3、用一个队列保存包含source的路线和公交车数量
 * 4、遍历队列为空
 */
public class LC815 {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        //m表示有m条公交线路
        int m = routes.length;
        boolean[][] edge = new boolean[m][m];
        Map<Integer,List<Integer>> map = new HashMap<>();
        for (int i=0;i<m;i++){
            for (int j :routes[i]){
                List<Integer> list = map.getOrDefault(j,new ArrayList<>());
                for (int k : list){
                    edge[i][k] = edge[k][i] = true;
                }
                list.add(i);
                map.put(j,list);
            }
        }
        int[] dist = new int[m];
        Arrays.fill(dist,-1);
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i : map.getOrDefault(source,new ArrayList<>())){
            dist[i] = 1;
            queue.add(i);
        }
        while (!queue.isEmpty()){
            int x = queue.poll();
            for (int y=0;y<m;y++){
                if (edge[x][y] && dist[y] == -1){
                    dist[y] = dist[x] + 1;
                    queue.add(y);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i :map.getOrDefault(target,new ArrayList<>())){
            if (dist[i]!=-1){
                min = Math.min(dist[i],min);
            }
        }
        return min==Integer.MAX_VALUE ? -1 : min;
    }

    public int numBusesToDestinationV2(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int m = routes.length;
        List<List<Integer>> list = new ArrayList<>();//保存每条线路上与哪条线路有相同的车站
        for (int i=0;i<m;i++){
            list.add(new ArrayList<>());
        }
        for (int i=0;i<m-1;i++){
            for (int j=i+1;j<m;j++){
                if (contains(routes[i],routes[j])){
                    list.get(i).add(j);
                    list.get(j).add(i);
                }
            }
        }
        Set<Integer> sources = new HashSet<>();
        Set<Integer> targets = new HashSet<>();
        Queue<Node> queue = new ArrayDeque<>();
        for (int i=0;i<m;i++){
            if (Arrays.binarySearch(routes[i],source)>=0){
                sources.add(i);
                queue.add(new Node(i,0));
            }
            if (Arrays.binarySearch(routes[i],target)>=0){
                targets.add(i);
            }
        }
        while (!queue.isEmpty()){
            Node node = queue.poll();
            int x = node.getX(),y = node.getY();
            if (targets.contains(x)){
                return y+1;
            }
            for (int j : list.get(x)){
                if (!sources.contains(j)){
                    queue.add(new Node(j,y+1));
                    sources.add(j);
                }
            }
        }
        return -1;
    }
    public boolean contains(int[] a,int[] b){
        int i=0,j=0;
        while (i<a.length && j<b.length){
            if (a[i]==b[j]){
                return true;
            }else if (a[i]<b[j]){
                i++;
            }else {
                j++;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        LC815 lc815 = new LC815();
        int ret = lc815.numBusesToDestinationV2(new int[][]{{1,2,7},{3,6,7}},1,6);
        System.out.println(ret);
    }
}
class Node{
    private int x;
    private int y;
    public Node(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}