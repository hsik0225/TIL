package com.hyunsiks.list.simply;

import java.util.NoSuchElementException;

public class SimplyLinkedList<E> {

    public SimplyNode<E> head;

    public int size;

    public SimplyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int search(E target) {
        SimplyNode<E> tempHead = this.head;

        for (int i = 0; i < size; i++) {
            if (target == tempHead.item) {
                return i;
            }
            tempHead = tempHead.nextSimplyNode;
        }

        return -1;
    }

    public void insertFront(E newItem) {
        this.head = new SimplyNode<>(this.head, newItem);
        size++;
    }

    // targetNode 다음에 새 노드 삽입
    public void insert(E newItem, SimplyNode<E> targetSimplyNode) {
        SimplyNode<E> nextSimplyNode = targetSimplyNode.nextSimplyNode;
        SimplyNode<E> simplyNode = new SimplyNode<>(nextSimplyNode, newItem);

        targetSimplyNode.setNextSimplyNode(simplyNode);

        size++;
    }

    public void deleteFront() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        this.head = this.head.nextSimplyNode;

        size--;
    }

    // targetNode가 가리키는 노드의 다음 노드를 삭제
    public void delete(SimplyNode<E> targetSimplyNode) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        SimplyNode<E> deleteSimplyNode = targetSimplyNode.nextSimplyNode;
        targetSimplyNode.setNextSimplyNode(deleteSimplyNode.nextSimplyNode);
        deleteSimplyNode.setNextSimplyNode(null);

        size--;
    }

    public void findAll() {
        StringBuilder stringBuilder = new StringBuilder();

        SimplyNode<E> simplyNode = this.head;

        for (int i = 0; i < size; i++) {
            stringBuilder.append(simplyNode.item).append(" ");
            simplyNode = simplyNode.nextSimplyNode;
        }

        System.out.println(stringBuilder.toString() + " : 길이 = " + size);
    }

    private boolean isEmpty() {
        return this.size == 0;
    }
}

