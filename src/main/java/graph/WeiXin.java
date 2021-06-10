package graph;

import java.util.*;
import java.util.stream.Collectors;

public class WeiXin {
    private int v;
    private LinkedList<Integer>[] adj;
    public WeiXin(UnDiGraph unDiGraph){
        this.v = unDiGraph.getVertex();
        this.adj = unDiGraph.getAdj();
    }
    /**
     * 搜索一条从s到t的路径
     */
    public void bfs(int s,int t){
        if (s == t){
            return;
        }
        boolean[] visited = new boolean[v];
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int[] prev = new int[v];
        for (int i=0;i<v;i++){
            prev[i] = -1;
        }
        while (!queue.isEmpty()){
            int w = queue.poll();
            for (int i=0;i<adj[w].size();i++){
                int q = adj[w].get(i);
                if (!visited[q]){
                    prev[q] = w;
                    if (q==t){//找到了
                        print(prev,s,t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }
    private void print(int[] prev,int s,int t){
        if (prev[t]!=-1 && t!=s){
            print(prev,s,prev[t]);
        }
        System.out.print(t+"");
    }
    public void queryMyFriends(int owner,int n){
        List<Integer> friends = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[v];
        int[] distance = new int[v];
        visited[owner] = true;
        distance[owner] = 0;
        queue.add(owner);
        while (!queue.isEmpty()){
            int id = queue.poll();
            LinkedList<Integer> ownerList = adj[id];
            for (int i=0;i<ownerList.size();i++){
                int j = ownerList.get(i);
                if (!visited[j]){
                    distance[j] = distance[id]+1;
                    friends.add(j);
                    queue.add(j);
                    visited[j] = true;
                }
            }
        }
        System.out.println(friends.stream().filter((f) -> distance[f]<=n).collect(Collectors.toList()));
    }
    public void queryMyFriends_2(int owner,int n){
        List<Integer> friends = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[v];
        visited[owner] = true;
        stack.add(owner);
        int depth = 0;
        while (!stack.isEmpty()){
            int id = stack.pop();
            LinkedList<Integer> ownerList = adj[id];
            for (int i=0;i<ownerList.size();i++){
                int j = ownerList.get(i);
                if (!visited[j]&&depth<n){
                    friends.add(j);
                    stack.add(j);
                    visited[j] = true;
                }
            }
        }
        System.out.println(friends);
    }
    /**
     * 比如微信中有10000人，ID为0-9999
     * 我的ID为7，有10个好友（17、27、37、47、57、67、77、87、97、107）
     * 其中17有7个好友（57、77、117、127、137、147、157）
     * 147有5个好友（167、177、187、197、207）
     * 问题：找到我的3度好友（包括1度、2度）
     * @param args
     */
    public static void main(String[] args) {
        UnDiGraph unDiGraph = new UnDiGraph(10000);
        unDiGraph.addEdge(7,17);
        unDiGraph.addEdge(7,27);
        unDiGraph.addEdge(7,37);
        unDiGraph.addEdge(7,47);
        unDiGraph.addEdge(7,57);
        unDiGraph.addEdge(7,67);
        unDiGraph.addEdge(7,77);
        unDiGraph.addEdge(7,87);
        unDiGraph.addEdge(7,97);
        unDiGraph.addEdge(7,107);
        unDiGraph.addEdge(17,57);
        unDiGraph.addEdge(17,77);
        unDiGraph.addEdge(17,117);
        unDiGraph.addEdge(17,127);
        unDiGraph.addEdge(17,137);
        unDiGraph.addEdge(17,147);
        unDiGraph.addEdge(17,157);
        unDiGraph.addEdge(147,167);
        unDiGraph.addEdge(147,177);
        unDiGraph.addEdge(147,187);
        unDiGraph.addEdge(147,197);
        unDiGraph.addEdge(147,207);
        unDiGraph.addEdge(207,217);
//        new WeiXin(unDiGraph).bfs(0,9);
        new WeiXin(unDiGraph).queryMyFriends(17,3);
    }
}
