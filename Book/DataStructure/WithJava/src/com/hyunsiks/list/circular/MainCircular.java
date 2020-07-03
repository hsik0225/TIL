package com.hyunsiks.list.circular;

public class MainCircular {

    public static void main(String[] args) {
        CircularLinkedList<String> list = new CircularLinkedList<>();
        list.insert("apple");
        list.insert("orange");
        list.insert("cherry");
        list.insert("pear");

        list.remove();
        list.remove();
        list.remove();
        list.remove();
    }
}
