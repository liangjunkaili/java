package graph;

import java.util.LinkedList;

/**
 * 无向图（邻接表）
 */
public class UnDiGraph {
    private int vertex;//顶点
    private LinkedList<Integer> adj[];//邻接表
    public UnDiGraph(int vertex){
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

    public int getVertex() {
        return vertex;
    }

    public LinkedList<Integer>[] getAdj() {
        return adj;
    }
//    private int edge;
//    private int degree;
//    private int in_degree;
//    private int out_degree;
//    private int weight;
}
