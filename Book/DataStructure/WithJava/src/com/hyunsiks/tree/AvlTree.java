package com.hyunsiks.tree;

public class AvlTree<Key extends Comparable<Key>, Value> {

    private AvlNode<Key, Value> root;

    public AvlTree(AvlNode<Key, Value> root) {
        this.root = root;
    }

    public AvlNode<Key, Value> getRoot() {
        return root;
    }

    public void setRoot(AvlNode<Key, Value> root) {
        this.root = root;
    }

    private AvlNode<Key, Value> rotateRight(AvlNode<Key, Value> node) {
        AvlNode<Key, Value> child = node.getLeft();

        node.setLeft(child.getRight());

        child.setRight(node);

        node.setHeight(tallerHeight(height(node.getLeft()), height(node.getRight())) + 1);
        child.setHeight(tallerHeight(height(child.getLeft()), height(child.getRight())) + 1);

        return node;
    }

    private AvlNode<Key, Value> rotateLeft(AvlNode<Key, Value> node) {
        AvlNode<Key, Value> child = node.getRight();

        node.setRight(child.getLeft());

        child.setLeft(node);

        node.setHeight(tallerHeight(height(node.getLeft()), height(node.getRight())) + 1);
        child.setHeight(tallerHeight(height(child.getLeft()), height(child.getRight())) + 1);

        return node;
    }

    public void put(Key key, Value value) {
        this.root = put(root, key, value);
    }

    private AvlNode<Key, Value> put(AvlNode<Key, Value> node, Key key, Value value) {
        if (node == null)
            return new AvlNode<>(key, value, 1);

        int compare = key.compareTo(node.getId());

        if (compare < 0)
            node.setLeft(put(node.getLeft(), key,value));

        else if (compare > 0)
            node.setRight(put(node.getRight(), key, value));

        else {

            // key가 이미 트리에 있으므로 value만 갱신
            node.setName(value);

            return node;
        }

        node.setHeight(tallerHeight(height(node.getLeft()), height(node.getRight())) + 1);

        // node 균현 점검 및 불균형을 바로 잡음
        return balance(node);
    }

    // 불균형 발생시 회전 연산을 통해 불균형을 해소한다
    private AvlNode<Key, Value> balance(AvlNode<Key, Value> node) {

        // node의 왼쪽 서브트리가 높아서 불균형 발생
        if (diffHeight(node) > 1 ) {

            // node의 왼쪽 자식노드의 오른쪽 서브트리가 높은 경우);
            if (diffHeight(node.getLeft()) < 0)
                node.setLeft(rotateLeft(node.getLeft()));

            node = rotateRight(node);
        }

        // node의 오른족 서브트리가 높아서 불균형 발생
        else if (diffHeight(node) < -1) {

            // node의 오른쪽 자식 노드의 왼쪽 서브트리가 높은 경우
            if (diffHeight(node.getRight()) > 0)
                node.setRight(rotateRight(node.getRight()));

            node = rotateLeft(node);
        }

        return node;
    }

    private int diffHeight(AvlNode<Key, Value> node) {

        return height(node.getLeft()) - height(node.getRight());
    }


    private int tallerHeight(int heightA, int heightB) {
        return 0;
    }

    public int height(AvlNode<Key, Value> node) {
        return node.getHeight();
    }
}
