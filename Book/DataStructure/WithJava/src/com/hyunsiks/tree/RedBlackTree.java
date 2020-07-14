package com.hyunsiks.tree;

public class RedBlackTree<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;
    private class Node {
        Key id;
        Value name;
        Node left, right;
        boolean color;

        public Node(Key id, Value name, boolean color) {
            this.id = id;
            this.name = name;
            this.color = color;
            left = right = null;
        }
    }

    private boolean isEmpty() {
        return this.root == null;
    }

    private boolean isRed(Node node) {

        // null의 색은 블랙
        if (node == null)
            return false;

        return node.color == RED;
    }

    public Value get(Key k) { return  get(root, k);}

    private Value get(Node node, Key k) {

        // 탐색 실패
        if (node == null) return null;

        int compare = node.id.compareTo(k);

        if (compare > 0) return get(node.left, k);
        else if (compare < 0) return get(node.right, k);
        else return node.name;
    }

    private Node rotateLeft(Node node) {
        Node rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        rightNode.color = node.color;
        node.color = RED;
        return rightNode;
    }

    private Node rotateRight(Node node) {
        Node leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        leftNode.color = node.color;
        node.color = RED;
        return leftNode;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    public void put(Key key, Value value) {
        this.root = put(root, key, value);
        this.root.color = BLACK;
    }

    private Node put(Node node, Key key, Value value) {

        // 새로운 레드 노드 생성
        if (node == null) return new Node(key, value, RED);

        int compare = key.compareTo(node.id);

        if (compare < 0) node.left = put(node.left, key, value);
        else if (compare > 0) node.right = put(node.right, key, value);
        else node.name = value;

        // 오른쪽 link가 레드인 경우 바로 잡는다
        if (!isRed(node.left) && isRed(node.right)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);
        return node;
    }

    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    public void deleteMin() {
        this.root = deleteMin(root);
        this.root.color = BLACK;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return null;
        if (!isRed(node.left) && !isRed(node.left.right))
            node = moveRedLeft(node);
        node.left = deleteMin(node.left);
        return fixUp(node);
    }

    private Node fixUp(Node node) {
        if (isRed(node.right)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.right)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);
        return node;
    }
}
