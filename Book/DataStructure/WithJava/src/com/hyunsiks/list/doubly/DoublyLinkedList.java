package com.hyunsiks.list.doubly;

import java.util.NoSuchElementException;

public class DoublyLinkedList<E> {

    private DoublyNode<E> head;

    private DoublyNode<E> tail;

    private int size;

    public DoublyNode<E> getHead() {
        return head;
    }

    public void setHead(DoublyNode<E> head) {
        this.head = head;
    }

    public DoublyNode<E> getTail() {
        return tail;
    }

    public void setTail(DoublyNode<E> tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public DoublyLinkedList() {
        this.head = new DoublyNode<>(null, null, null);
        this.tail = new DoublyNode<>(null, head, null);
        head.setNextDoublyNode(tail);
    }

    // node가 가리키는 노드 앞에 삽입
    public void insertBefore(DoublyNode<E> node, E item) {
         DoublyNode<E> previousNode = node.previousDoublyNode;
         DoublyNode<E> newNode = new DoublyNode<>(previousNode, node, item);
         previousNode.setNextDoublyNode(newNode);
         node.setPreviousDoublyNode(newNode);
         size++;
    }

    // node가 가리키니는 노드 뒤에 삽입
    public void insertAfter(DoublyNode<E> node, E item) {
        DoublyNode<E> nextNode = node.nextDoublyNode;
        DoublyNode<E> newNode = new DoublyNode<>(node, nextNode, item);
        node.setNextDoublyNode(newNode);
        nextNode.setPreviousDoublyNode(newNode);
        size++;
    }

    // node 삭제
    public void delete(DoublyNode<E> node) {
        if (node == null)
            throw new NoSuchElementException();

        DoublyNode<E> previousNode = node.previousDoublyNode;
        DoublyNode<E> nextNode = node.nextDoublyNode;

        previousNode.setNextDoublyNode(nextNode);
        nextNode.setPreviousDoublyNode(previousNode);
        size--;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "리스트 비어있음";
        }

        DoublyNode<E> temp = this.head;

        StringBuilder stringBuilder = new StringBuilder();

        while (temp != null) {
            stringBuilder.append(temp.item).append(" ");
            temp = temp.nextDoublyNode;
        }

        return stringBuilder.toString();
    }
}