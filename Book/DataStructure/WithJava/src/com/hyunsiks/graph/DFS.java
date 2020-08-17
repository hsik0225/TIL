package com.hyunsiks.graph;

import java.util.List;

public class DFS {

    // 그래프 정점의 수
    int N;

    List<Edge>[] graph;

    // DFS 수행 중 방문한 정점을 true로 만든다
    private boolean[] visited;

    // 생성자
    public DFS(List<Edge>[] adjList) {
        N = adjList.length;
        graph = adjList;
        visited = new boolean[N];
        for (int i = 0; i < N; i++)
            if (!visited[i])
                dfs(i);
    }

    private void dfs(int i) {

        // 정점 i가 방문되어 visited[i]를 true로
        visited[i] = true;

        // 정점 i가 방문되었음을 출력
        System.out.println(i + " ");

        // 정점 i에 인접한 각 정점에 대해
        for (Edge e : graph[i]) {

            // 정점 i에 인접한 정점이 방문이 안되었으면 재귀 호출
            if(!visited[e.adjvertex])
                dfs(e.adjvertex);
        }
    }

    private static class Edge {

        // 간선의 다른쪽 정점
        int adjvertex;

        public Edge(int v) {
            this.adjvertex = v;
        }
    }
}
