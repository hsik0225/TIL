package com.hyunsiks.tree;

public class UnionFind {

    protected DisjointNode[] nodes;

    public UnionFind(DisjointNode[] nodes) {
        this.nodes = nodes;
    }

    // 찾는 값이 속한 집합의 루트를 재귀적으로 찾고 경로 상의 각 원소의 부모를 루트로 만든다
    protected int find(int findValue) {
        if ( findValue != nodes[findValue].getParent())
            nodes[findValue].setParent(find(nodes[findValue].getParent()));;

        return nodes[findValue].getParent();
    }

    public void union(int itemA, int itemB) {

        int rootItemOfItemA = find(itemA);
        int rootItemOfItemB = find(itemA);

        // 루트 노드가 동일하면 더 이상의 수행 없이 그대로 리턴
        if (rootItemOfItemA == rootItemOfItemB)
            return;

        // rank가 높은 루트 노드가 승자가 된다
        if (nodes[rootItemOfItemA].getRank() > nodes[rootItemOfItemB].getRank())
            nodes[rootItemOfItemB].setParent(rootItemOfItemA);
        else if (nodes[rootItemOfItemA].getRank() < nodes[rootItemOfItemB].getRank())
            nodes[rootItemOfItemA].setParent(rootItemOfItemB);
        else {
            // 둘 중에 하나 임의로 승자
            nodes[rootItemOfItemA].setParent(rootItemOfItemB);

            int rank = nodes[rootItemOfItemB].getRank();

            nodes[rootItemOfItemB].setRank(rank);
        }
    }
}
