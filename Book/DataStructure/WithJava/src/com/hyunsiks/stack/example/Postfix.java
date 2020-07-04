package com.hyunsiks.stack.example;

import java.util.Stack;

public class Postfix {

    public static void main(String[] args) {
        String expression = "832+1-/";

        System.out.println(postfix(expression));
    }

    private static int postfix(String expression) {
        Stack<Integer> stack = new Stack<>();

        char[] expressionChars = expression.toCharArray();

        for (char letter : expressionChars) {
            if (Character.isDigit(letter)) {
                stack.push(letter - '0');

            } else {
                int a = stack.pop();
                int b = stack.pop();

                if (letter == '+')
                    stack.push(a + b);
                else if (letter == '-')
                    stack.push(b - a);
                else  if (letter == '*')
                    stack.push(a * b);
                else
                    stack.push(b / a);
            }
        }

        return stack.pop();
    }
}
