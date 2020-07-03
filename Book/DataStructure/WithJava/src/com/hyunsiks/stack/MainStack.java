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

        System.out.println(" ============= List Stack =============");

        ListStack<String> listStack = new ListStack<>();
        listStack.push("apple");
        listStack.push("orange");
        listStack.push("cherry");
        listStack.push("pear");

        listStack.pop();
        listStack.pop();
        listStack.pop();
        listStack.pop();
    }
}
