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

    public void deleteMin() {
        if (this.root == null)
            System.out.println("This is a Empty Tree");

        this.root = deleteMin(this.root);
    }

    private BinaryTreeNode<Key, Value> deleteMin(BinaryTreeNode<Key, Value> node) {
        if (node.getLeft() == null)
            return node.getRight();

        // deleteMin(node.getLeft()) 는 최소값 노드의 오른쪽 자식이 된다
        // 최소값 노드의 자식을 최소값 노드 부모의 왼쪽 자식에 연결시킨다
        node.setLeft(deleteMin(node.getLeft()));
        return node;
    }

    public void delete(Key key) {
        this.root = delete(this.root, key);
    }

    private BinaryTreeNode<Key, Value> delete(BinaryTreeNode<Key, Value> node, Key key) {
        if (node == null)
            return null;

        int compare = node.getId().compareTo(key);

        // 삭제할 값이 현재 노드의 값보다 작다
        // 왼쪽 자식으로 이동
        if (compare > 0)
            node.setLeft(delete(node.getLeft(), key));

        // 삭제할 값이 현재 노드의 값보다 크다
        // 오른쪽 자식으로 이동
        else if (compare < 0)
            node.setRight(delete(node.getRight(), key));

        // 삭제할 노드 발견
        else {

            // case 0, 1
            if (node.getRight() == null)
                return node.getLeft();

            // case 1
            if (node.getLeft() == null)
                return node.getRight();

            BinaryTreeNode<Key, Value> target = node;

            // case 2
            // 삭제할 노드 자리로 옮겨올 노드 찾아서 n이 가리키게 함
            // 삭제될 노드의 중위 후속자를 min() 메소드를 호출하여 찾는다
            node = min(target.getRight());

            // deleteMin() 메소드를 호출하여 중위 후속자를 트리에서 분리시키고
            // 중의 후속자의 부모와 자식을 연결시킨 뒤
            // 계속해여 재 연결하며 거슬러 올라간다
            // 최종적으로 삭제되는 노드(target)의 오른쪽 자식 노드의 레퍼런스를 리턴다

            node.setRight(deleteMin(target.getRight()));

            // 최정적으로 삭제되는 노드의 왼쪽 자식을 중위 후속자의 왼쪽 자식으로 만든
            node.setLeft(target.getLeft());
        }

        return node;
    }
}
