package com.hyunsiks.list.doubly;

import java.util.NoSuchElementException;

public class DoublyLinkedList<E> {

    private DoublyNode<E> head;

    private DoublyNode<E> tail;

    private int size;

    public DoublyLinkedList() {
        this.head = new DoublyNode<>(null, null, null);
        this.tail = new DoublyNode<>(null, head, null);
    }

    public void addFront(E item) {
        if (isEmpty()) {
            this.head = new DoublyNode<>(item);

        } else {
            DoublyNode<E> frontDoublyNode = this.head;
            this.head = new DoublyNode<>(null, frontDoublyNode, item);
            this.head.nextDoublyNode.setPreviousDoublyNode(frontDoublyNode);
        }

        size++;

        System.out.println(toString());
    }

    public void addEnd(E item) {
        if (isEmpty()) {
            this.head = new DoublyNode<>(item);

        } else {
            DoublyNode<E> temp = this.head;

            // Traverse till end of list
            while (temp.nextDoublyNode != null) {
                temp = temp.nextDoublyNode;
            }

            temp.nextDoublyNode = new DoublyNode<>(temp, null, item);
        }

        size++;

        System.out.println(toString());
    }

    public void addBefore(E itemA, E itemB) {

        // List is empty, can't add
        if (isEmpty()) {
            throw new NoSuchElementException("Element " + itemA.toString() + " not found");
        }

        DoublyNode<E> current = searchNode(itemA);

        DoublyNode<E> newDoublyNode = new DoublyNode<>(current.previousDoublyNode, current, itemB);

        if(current.previousDoublyNode != null) {
            current.previousDoublyNode.setNextDoublyNode(newDoublyNode);
        } else {
            this.head = newDoublyNode;
        }

        current.previousDoublyNode = newDoublyNode;

        size ++;

        System.out.println(toString());
    }

    public void addAfter(E itemA, E itemB) {

        // List is empty, can't add
        if (isEmpty()) {
            throw new NoSuchElementException("Element " + itemA.toString() + " not found");
        }

        DoublyNode<E> current = searchNode(itemA);

        DoublyNode<E> newDoublyNode = new DoublyNode<>(current, current.nextDoublyNode, itemB);

        if (current.nextDoublyNode != null) {
            current.nextDoublyNode.setPreviousDoublyNode(newDoublyNode);
        }

        current.nextDoublyNode = newDoublyNode;

        size++;

        System.out.println(toString());
    }

    public void remove(E item) {

        // List is empty, can't remove
        if (isEmpty()) {
            throw new NoSuchElementException("Element " + item.toString() + " not found");
        }

        // Removing front element
        if (head.item.equals(item)) {
            this.head = this.head.nextDoublyNode;
            System.out.println(toString());
            return;
        }

        DoublyNode<E> current = searchNode(item);

        // It has a next pointer, so not the last node
        if (current.nextDoublyNode != null) {
            current.nextDoublyNode.setPreviousDoublyNode(current.previousDoublyNode);
        }

        current.previousDoublyNode.setNextDoublyNode(current.nextDoublyNode);

        size--;

        System.out.println(toString());
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public DoublyNode<E> searchNode(E item) {
        DoublyNode<E> current = head;

        while (current != null && !current.item.equals(item)) {
            current = current.nextDoublyNode;
        }

        // if null, not fount
        if (current == null) {
            throw new NoSuchElementException("Element " + item.toString() + " not found");
        }

        return current;
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