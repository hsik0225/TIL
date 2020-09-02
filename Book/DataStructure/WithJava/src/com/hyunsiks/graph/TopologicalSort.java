package com.hyunsiks.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopologicalSort {

    // 그래프의 정점 수
    int N;

    // DFS 수행 중 방문 여부 체크 용
    boolean[] visited;

    // 인접리스트 형태의 입력 그래프
    List<Integer>[] adjList;

    // 위상정렬 순서를 담을 리스트
    List<Integer> sequence;

    // 생성자
    public TopologicalSort(List<Integer>[] graph) {
        N = graph.length;
        visited = new boolean[N];
        adjList = graph;
        sequence = new ArrayList<>();
    }

    // 위상정렬을 위한 DFS 수행
    public List<Integer> sort() {
        for (int i = 0; i < N; i++) {
            if (!visited[i])
                dfs(i);
        }

        // sequence를 역순으로 만들기
        Collections.reverse(sequence);
        return sequence;
    }

    // DFS 수행
    private void dfs(int i) {
        visited[i] = true;

        // i의 방문이 끝나고 앞으로 방문해야하는 각 정점 v에 대해
        for (int v : adjList[i]) {
            if (!visited[v])
                dfs(v);
        }

        // i에서 진출하는 간선이 더 이상 없으므로 i를 sequence에 추가
        sequence.add(i);
    }

    public static void main(String[] args) {
        System.out.println(new char[]{'a','b','c'});
    }
}
