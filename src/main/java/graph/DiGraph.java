package graph;

import java.util.LinkedList;

/**
 * 有向图（邻接表）
 */
public class DiGraph {
    private int vertex;//顶点
    private LinkedList<Integer> adj[];//邻接表
    public DiGraph(int vertex){
        this.vertex = vertex;
        adj = new LinkedList[vertex];
        for (int i=0;i<vertex;i++){
            adj[i] = new LinkedList<>();
        }
    }
    public void addEdge(int begin,int end){
        adj[begin].add(end);
        adj[end].add(begin);
    }
}
