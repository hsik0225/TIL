package com.hyunsiks.list.doubly;

public class MainDoubly {
    public static void main(String[] args) {

        DoublyLinkedList<String> doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.addFront("apple");
        doublyLinkedList.addAfter("apple", "pear");
        doublyLinkedList.addAfter("pear", "cherry");
        doublyLinkedList.addBefore("cherry", "orange");

        doublyLinkedList.remove("cherry");

        doublyLinkedList.addAfter("orange", "grape");

        doublyLinkedList.remove("apple");

        doublyLinkedList.remove("pear");

        doublyLinkedList.remove("orange");

        doublyLinkedList.remove("grape");
    }
}
