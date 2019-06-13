package com.algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 图的基本实现 (无向图)
 *   存储：邻接矩阵、邻接表
 *   广度优先搜索、深度优先搜索
 *
 * eg:
 *  0---1---2
 *  |   |   |
 *  3---4---5
 *      |   |
 *      6---7
 *
 *
 *
 */
public class Graph {

    // 顶点的个数
    private int v;

    // 邻接表信息
    private LinkedList<Integer>[] adj;

    public Graph(int v) {
        this.v = v;

        adj = new LinkedList[v];

        for (int i = 0; i < v; i++){
            adj[i] = new LinkedList<>();
        }
    }

    private boolean found = false;

    /**
     * 在无向图中一条边存2次
     * @param s
     * @param t
     */
    public void add(int s, int t){
        adj[s].add(t);
//        adj[t].add(s);

    }

    /**
     * 广度优先算法
     * @param s  源顶点s
     * @param t  目标顶点t
     */
    public void bfs(int s, int t){
        if(s == t){
            return;
        }
        // 用来标记已经访问过顶点，避免重复访问
        boolean[] visited = new boolean[v];
        visited[s] = true;
        // 队列用来存储已经被访问、但是相邻顶点还没有被访问的顶点
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        // 记录搜索的路径
        int[] prev = new int[v];
        // 记录搜索的路径，初始化为-1
        for(int i = 0; i < v; i++){
            prev[i] = -1;
        }
        while (queue.size() != 0){
            int w = queue.poll();
            // 遍历顶点链
            for(int i = 0; i < adj[w].size(); ++i){
                // 遍历当前顶点关联的顶点
                int q = adj[w].get(i);
                if(!visited[q]){
                    prev[q] = w;
                    if(q == t){
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);

                }
            }
            System.out.println();
        }

    }

    /**
     * 递归打印  s->t 的路径
     * @param prev
     * @param s
     * @param t
     */
    private void print(int[] prev, int s, int t){
        if(prev[t] != -1 && t != s){
            print(prev, s, prev[t]);
        }
        System.out.println(t + "  ");
    }


    /**
     * 深度优先搜索
     * @param s
     * @param t
     */
    public void dfs(int s, int t){

        if(s == t){
            return;
        }
        boolean[] visited = new boolean[v];

        int[] prev = new int[v];
        for(int i = 0; i < v; i++){
            prev[i] = -1;
        }

        recurDFS(s, t, visited, prev);
        print(prev, s, t);

    }


    private void recurDFS(int s, int t, boolean[] visited, int[] prev){

        System.out.println("当前顶点：" + s);
        if(found){
            return;
        }

        visited[s] = true;
        if(s == t){
            found = true;
            return;
        }
        for(int i = 0; i < adj[s].size(); i++){
            int q = adj[s].get(i);
            if(!visited[q]){
                prev[q] = s;
                recurDFS(q, t, visited, prev);
            }

        }


    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);

        graph.add(0, 1);
        graph.add(0, 3);
        graph.add(1, 2);
        graph.add(1, 4);
        graph.add(2, 5);
        graph.add(3, 4);
        graph.add(4, 5);
        graph.add(4, 6);
        graph.add(5, 7);
        graph.add(6, 7);


        graph.bfs(0, 7);
//        graph.dfs(0, 7);

    }

}
