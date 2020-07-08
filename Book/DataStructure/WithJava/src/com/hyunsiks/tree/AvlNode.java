package com.hyunsiks.tree;

public class AvlNode<Key extends Comparable<Key>, Value> {

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

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public Value getName() {
        return name;
    }

    public void setName(Value name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AvlNode<Key, Value> getLeft() {
        return left;
    }

    public void setLeft(AvlNode<Key, Value> left) {
        this.left = left;
    }

    public AvlNode<Key, Value> getRight() {
        return right;
    }

    public void setRight(AvlNode<Key, Value> right) {
        this.right = right;
    }
}
