package com.hyunsiks.list;

import java.util.NoSuchElementException;

public class SimplyLinkedList<E> {

    class Node<E> {
        private Node<E> nextNode;

        private E item;

        public Node(Node<E> nextNode, E item) {
            this.nextNode = nextNode;
            this.item = item;
        }

        public Node<E> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }

        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
        }
    }

    private Node<E> head;

    private int size;

    public SimplyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int search(E target) {
        Node<E> tempHead = this.head;

        for (int i = 0; i < size; i++) {
            if (target == tempHead.item) {
                return i;
            }
            tempHead = tempHead.nextNode;
        }

        return -1;
    }

    public void insertFront(E newItem) {
        this.head = new Node<>(this.head, newItem);
        size++;
    }

    // targetNode 다음에 새 노드 삽입
    public void insert(E newItem, Node<E> targetNode) {
        Node<E> nextNode = targetNode.nextNode;
        Node<E> node = new Node<>(nextNode, newItem);

        targetNode.setNextNode(node);

        size++;
    }

    public void deleteFront() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        this.head = this.head.nextNode;

        size--;
    }

    // targetNode가 가리키는 노드의 다음 노드를 삭제
    public void delete(Node<E> targetNode) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node<E> deleteNode = targetNode.nextNode;
        targetNode.setNextNode(deleteNode.nextNode);
        deleteNode.setNextNode(null);

        size--;
    }

    public void findAll() {
        StringBuilder stringBuilder = new StringBuilder();

        Node<E> node = this.head;

        for (int i = 0; i < size; i++) {
            stringBuilder.append(node.item + " ");
            node = node.nextNode;
        }

        System.out.println(stringBuilder.toString() + " : 길이 = " + size);
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    public static void main(String[] args) {
        SimplyLinkedList<String> linkedList = new SimplyLinkedList<>();
        linkedList.insertFront("cherry");
        linkedList.insertFront("orange");
        linkedList.insertFront("pear");
        linkedList.insert("apple", linkedList.head);

        linkedList.findAll();

        linkedList.insert("kiwi", linkedList.head.nextNode.nextNode);

        linkedList.findAll();

        System.out.println("체리가 " + linkedList.search("cherry") + " 번째 있다");
        System.out.println("키위가 " + linkedList.search("kiwi") + " 번째 있다");
    }
}

