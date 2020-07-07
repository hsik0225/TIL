package com.hyunsiks.tree;

public class BST<Key extends Comparable<Key>, Value> {

    public BinaryTreeNode<Key, Value> root;

    public BinaryTreeNode<Key, Value> getRoot() {
        return root;
    }

    public BST(BinaryTreeNode<Key, Value> root) {
        this.root = root;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public Value get(BinaryTreeNode<Key, Value> node, Key key) {
        // key를 발견하지 못함
        if (node != null)
            return null;

        int compare = node.getId().compareTo(key);

        // 만약 노드의 Id가 key 보다 크다면
        if (compare > 0)
            return get(node.getLeft(), key);

        // 만약 노드의 Id가 key 보다 작다면
        else if (compare < 0)
            return get(node.getRight(), key);

        // 만약 노드의 Id와 key 가 같다면
        else
            return node.getName();
    }

    public void put(Key key, Value value) {

        // root 가 put() 메소드가 리턴하는 Node를 가리키도록 한다
        this.root = put(this.root, key, value);
    }

    private BinaryTreeNode<Key, Value> put(BinaryTreeNode<Key, Value> node, Key key, Value value) {
        if (node == null)
            return new BinaryTreeNode<>(key, value);

        int compare = node.getId().compareTo(key);

        // Id가 key 보다 크면 왼쪽 서브 트리에 삽입
        if (compare > 0)
            node.setLeft(put(node.getLeft(), key, value));

        // Id가 key 보다 작으면 오른쪽 서브 트리에 삽입
        else if (compare < 0)
            node.setRight(put(node.getRight(), key, value));

        else
            node.setName(value);

        return node;
    }

    public Key min() {
        if (root == null)
            return null;

        return min(root).getId();
    }

    private BinaryTreeNode<Key, Value> min(BinaryTreeNode<Key,Value> node) {
        if (node.getLeft() == null)
            return node;

        return min(node.getLeft());
    }
}
