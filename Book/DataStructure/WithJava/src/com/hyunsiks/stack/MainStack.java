package com.hyunsiks.stack;

public class MainStack {

    public static void main(String[] args) {
        ArrayStack<String> stack = new ArrayStack<>();
        stack.push("apple");
        stack.push("orange");
        stack.push("cherry");
        stack.push("pear");

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
    }
}
