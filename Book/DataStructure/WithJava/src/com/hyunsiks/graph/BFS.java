package com.hyunsiks.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

    // 그래프 정점의 수
    int N;

    List<Edge>[] graph;

    // BFS 수행 중 방문한 정점의 원소를 true로 만든다
    private boolean[] visited;

    // 생성자
    public BFS(List<Edge>[] adjList) {
        N = adjList.length;
        graph = adjList;
        visited = new boolean[N];
        for (int i = 0; i < N; i++)
            if (!visited[i])
                bfs(i);
    }

    private void bfs(int i) {
        Queue<Integer> queue = new LinkedList<>();
        visited[i] = true;

        // 큐에 시작 정점 삽입
        queue.add(i);

        while (!queue.isEmpty()) {
            int j = queue.remove();
            System.out.println(j + " ");

            // 정점 j에 인접한 정점들 중 방문안된 정점 하나씩 방문
            for (Edge e : graph[j]) {
                if (!visited[e.adjvertex]) {
                    visited[e.adjvertex] = true;

                    // 새로 방문된 정점을 큐에 삽입
                    queue.add(e.adjvertex);
                }
            }
        }
    }

    private static class Edge {
        int adjvertex;

        public Edge(int v) {
            this.adjvertex = v;
        }
    }
}
