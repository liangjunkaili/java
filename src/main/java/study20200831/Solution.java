package study20200831;

import java.util.*;

/**
 * 841. 钥匙和房间
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 * 你可以自由地在房间之间来回走动。
 * 如果能进入每个房间返回 true，否则返回 false。
 * 示例 1：
 * 输入: [[1],[2],[3],[]]
 * 输出: true
 * 解释:
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 * 示例 2：
 * 输入：[[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 * 提示：
 * 1 <= rooms.length <= 1000
 * 0 <= rooms[i].length <= 1000
 * 所有房间中的钥匙数量总计不超过 3000。
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.canVisitAllRooms(
                Arrays.asList(Arrays.asList(1,3),Arrays.asList(1,3,0),Arrays.asList(2),Arrays.asList(0))));
    }
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        List<Integer> visitRoom = new ArrayList<>();
        Set<Integer> keys = new HashSet<>();
        keys.add(0);
        visitRoom(0,rooms,visitRoom,keys);
        if (keys.size()==rooms.size())
            return true;
        return false;
    }
    public boolean canVisitAllRooms_v3(List<List<Integer>> rooms) {
        int n = rooms.size(), num = 0;
        boolean[] vis = new boolean[n];
        Queue<Integer> que = new LinkedList<Integer>();
        vis[0] = true;
        que.offer(0);
        while (!que.isEmpty()) {
            int x = que.poll();
            num++;
            for (int it : rooms.get(x)) {
                if (!vis[it]) {
                    vis[it] = true;
                    que.offer(it);
                }
            }
        }
        return num == n;
    }
    boolean[] vis;//标记是否访问过
    int num;//代表访问的节点数
    public boolean canVisitAllRooms_v2(List<List<Integer>> rooms) {
        int n = rooms.size();
        vis = new boolean[n];
        num = 0;
        dfs(rooms,0);
        return n==num;
    }
    //深度优先搜索
    public void dfs(List<List<Integer>> rooms,int x){
        vis[x] = true;
        num++;
        for (int i:rooms.get(x)){
            if (!vis[i]){
                dfs(rooms,i);
            }
        }
    }
    public void visitRoom(int index,List<List<Integer>> rooms,List<Integer> visitRoom,Set<Integer> keys){
        List<Integer> room = rooms.get(index);
        if (visitRoom.contains(index)){
            return;
        }
        visitRoom.add(index);
        for (int j=0;j<room.size();j++){
            int idx = room.get(j);
            keys.add(idx);
            if (!visitRoom.contains(idx)){
                visitRoom(idx,rooms,visitRoom,keys);
            }
        }
    }
}
