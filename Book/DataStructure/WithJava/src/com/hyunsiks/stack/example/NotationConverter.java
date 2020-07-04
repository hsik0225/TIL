package com.hyunsiks.stack.example;

import java.util.Stack;

public class NotationConverter {

    public static void main(String[] args) {
        String expression = "A*(B+C/D)";

        System.out.println(infix2postfix(expression));
    }

    private static String infix2postfix(String expression) {
        StringBuilder stringBuilder = new StringBuilder();

        Stack<Character> stack = new Stack<>();

        String operations = "+-*/";

        char[] expressionChars = expression.toCharArray();

        for (char letter : expressionChars) {

            if (letter == '(') {
                System.out.println("( before stack = " + stack);

                stack.push(letter);

                System.out.println("( after stack = " + stack);
            }

            else if (letter == ')') {
                System.out.println(") before stack = " + stack);

                char item =  stack.pop();

                while (item != '(') {
                    stringBuilder.append(item);
                    item = stack.pop();
                }

                System.out.println(") after stack = " + stack);
            }

            else if (!operations.contains(Character.toString(letter)))
                stringBuilder.append(letter);

            else {
                System.out.println("operation before stack = " + stack);

                // 읽은 문자가 stack의 항목들 중 가장 우선순위가 높아야 한다
                // 그러므로 스택의 항목이 읽은 문자보다 우선순위가 같거나 높으면 다 pop
                while (!stack.isEmpty() && isOverPriority(stack.peek(), letter))
                    stringBuilder.append(stack.pop());

                stack.push(letter);

                System.out.println("operation after stack = " + stack);
            }
        }

        while (!stack.isEmpty())
            stringBuilder.append(stack.pop());

        return stringBuilder.toString();
    }

    // peek() 의 항목보다 읽은 문자의 우선순위가 더 높다면
    private static boolean isOverPriority(char item, char letter) {
        return getPriority(item) >= getPriority(letter);
    }

    private static int getPriority(char operation) {
        if (operation == '*' || operation == '/')
            return 5;
        else if (operation == '+' || operation == '-')
            return 3;
        else if (operation == '(' || operation == ')')
            return 1;
        return -1;
    }
}
