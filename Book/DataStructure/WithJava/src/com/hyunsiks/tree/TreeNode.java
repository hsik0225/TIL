package com.hyunsiks.tree;

// Comparable 인터페이스는 compareTO() 메소드를 통해 2개의 키를 비교한다
public class TreeNode<Key extends Comparable<Key>> {

    private Key item;

    private TreeNode<Key> left;

    private TreeNode<Key> right;

    public TreeNode(Key item, TreeNode<Key> left, TreeNode<Key> right) {
        this.item = item;
        this.left = left;
        this.right = right;
    }

    public Key getItem() {
        return item;
    }

    public void setItem(Key item) {
        this.item = item;
    }

    public TreeNode<Key> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<Key> left) {
        this.left = left;
    }

    public TreeNode<Key> getRight() {
        return right;
    }

    public void setRight(TreeNode<Key> right) {
        this.right = right;
    }
}
