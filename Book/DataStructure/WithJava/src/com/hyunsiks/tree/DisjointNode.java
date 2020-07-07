package com.hyunsiks.tree;

public class DisjointNode {

    // 노드의 부모 레퍼런스를 저장한다
    private int parent;

    private int rank;

    public DisjointNode(int parent, int rank) {
        this.parent = parent;
        this.rank = rank;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
