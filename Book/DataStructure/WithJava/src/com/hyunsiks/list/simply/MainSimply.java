package com.hyunsiks.list.simply;

public class MainSimply {

    public static void main(String[] args) {
        SimplyLinkedList<String> linkedList = new SimplyLinkedList<>();
        linkedList.insertFront("cherry");
        linkedList.insertFront("orange");
        linkedList.insertFront("pear");
        linkedList.insert("apple", linkedList.head);

        linkedList.findAll();

        linkedList.insert("kiwi", linkedList.head.nextSimplyNode.nextSimplyNode);

        linkedList.findAll();

        System.out.println("체리가 " + linkedList.search("cherry") + " 번째 있다");
        System.out.println("키위가 " + linkedList.search("kiwi") + " 번째 있다");
    }
}
