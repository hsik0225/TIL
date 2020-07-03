package com.hyunsiks.list.circular;

import java.util.NoSuchElementException;

public class CircularLinkedList<E> {

    public CircularNode<E> lastNode;

    public int size;

    public CircularLinkedList() {
        this.lastNode = null;
        this.size = 0;
    }

    public void setLastNode(CircularNode<E> lastNode) {
        this.lastNode = lastNode;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void insert(E item) {
        CircularNode<E> newNode = new CircularNode<>(null, item);

        if (lastNode == null) {
            newNode.setNextNode(newNode);
            this.lastNode = newNode;
        } else {

            // newNode의 다음 노드가 last가 가리키는 노드의 다음노드가 되도록
            newNode.setNextNode(this.lastNode.nextNode);

            // last가 가리키는 노드의 다음 노드가 newNode가 되도록
            lastNode.setNextNode(newNode);
        }

        size++;

        System.out.println(toString());
    }

    // last가 가리키는 노드의 다음 노드를 제거
    public CircularNode<E> remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        CircularNode<E> node = this.lastNode.nextNode;

        // 노드가 1개밖에 없을 경우
        if (this.lastNode == node) {
            this.lastNode = null;
        } else {
            lastNode.setNextNode(node.nextNode);

            node.setNextNode(null);
        }

        size--;

        System.out.println(toString());

        return node;
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        CircularNode<E> current = this.lastNode;

        for (int i = 0; i < size; i++) {
            stringBuilder.append(current.item).append(" ");
            current = current.nextNode;
        }

        return stringBuilder.toString();
    }
}
