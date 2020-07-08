package com.hyunsiks.tree;

public class AvlNode<Key, Value> {

    private Key id;

    private Value name;

    private int height;

    private AvlNode<Key, Value> left;

    private AvlNode<Key, Value> right;

    public AvlNode(Key id, Value name, int height) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.left = null;
        this.right = null;
    }
}
