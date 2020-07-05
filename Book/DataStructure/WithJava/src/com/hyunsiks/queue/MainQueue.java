package com.hyunsiks.queue;

public class MainQueue {

    public static void main(String[] args) {
        ArrayQueue<String> queue = new ArrayQueue<>();
        queue.add("apple");
        queue.add("orange");
        queue.add("cherry");
        queue.add("pear");

        queue.remove();

        queue.add("grape");
        queue.add("lemon");
        queue.add("mango");
        queue.add("lime");
        queue.add("kiwi");

        System.out.println("======================= List Queue Start ========================");

        ListQueue<String> listQueue = new ListQueue<>();
        listQueue.add("apple");
        listQueue.add("orange");
        listQueue.add("cherry");
        listQueue.add("pear");

        listQueue.remove();
        listQueue.remove();

        listQueue.add("grape");
    }
}
