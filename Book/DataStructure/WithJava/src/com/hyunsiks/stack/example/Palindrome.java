package com.hyunsiks.stack.example;

import java.util.Stack;

public class Palindrome {

    public static void main(String[] args) {
        String string = "RACECAR";

        System.out.println(isPalindrome(string));
    }

    private static boolean isPalindrome(String string) {
        Stack<Character> stack = new Stack<>();

        int length = string.length();

        char[] front = string.substring(0, length/2).toCharArray();

        for (char item : front) {
            stack.push(item);
        }

        int startIndex = length%2 == 1 ? (length/2) + 1 : length/2;

        char[] end = string.substring(startIndex, length).toCharArray();

        for (char item : end) {
            char storedItem = stack.pop();

            if (item != storedItem) {
                return false;
            }
        }

        return true;
    }
}
