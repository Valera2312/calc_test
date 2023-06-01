package com.company;

import java.util.*;

public class Calculator {

    private static final Map<Character, Operation> operations = new HashMap<>();

    static {
        operations.put('+', new Addition());
        operations.put('-', new Subtraction());
        operations.put('*', new Multiplication());
        operations.put('/', new Division());
    }

    public Double calculate(String expression) {
       return RPNtoAnswer(expressionToRPN(expression));
    }

    private static String expressionToRPN(String expression) {

        Stack<Character> stack = new Stack<>();
        StringBuilder current = new StringBuilder();

        int priority;
        for (int i = 0; i < expression.length(); i++) {

            char currentChar = expression.charAt(i);
            priority = precedence(currentChar);

            if (priority == 0) {
                current.append(currentChar);
            }
            if (priority == 1) {
                stack.push(currentChar);
            }
            if (priority > 1) {
                current.append(' ');
                while (!stack.empty()) {
                    if (precedence(stack.peek()) >= priority) current.append(stack.pop());
                    else break;
                }
                stack.push(currentChar);
            }
            if (priority == -1) {
                while (precedence(stack.peek()) != 1) {
                    current.append(' ').append(stack.pop());
                }
                stack.pop();
            }
        }
        while (!stack.empty()) {
            if (precedence(stack.peek()) == 1) {
                throw new IllegalArgumentException("Unbalanced parentheses");
            }
            current.append(' ').append(stack.pop());
        }
        return current.toString();
    }

    private static double RPNtoAnswer(String rpn) {
        Deque<Double> stack = new ArrayDeque<>();
        StringBuilder operand = new StringBuilder();
        int priority;

        for (int i = 0; i < rpn.length(); i++) {

            char currentChar = rpn.charAt(i);
            priority = precedence(currentChar);


            if(currentChar == ' ') continue;

            if(priority == 0) {
                while (rpn.charAt(i) != ' ' && precedence(rpn.charAt(i))== 0) {
                    operand.append(rpn.charAt(i++));
                    if(i == rpn.length()) break;
                }
                stack.push(Double.parseDouble(operand.toString()));
                operand = new StringBuilder();
            }
            if(precedence(currentChar) > 1) {
                if (!operations.containsKey(currentChar)) {
                    throw new IllegalArgumentException("Invalid operator: " + currentChar);
                }
                double a = stack.pop(), b = stack.pop();
                Operation operation = operations.get(currentChar);
                double result = operation.perform(a, b);
                stack.push(result);
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }
        return stack.pop();
    }

    private static int precedence(char token) {
        if(token == '*' || token == '/') return 3;
        else if(token == '+' || token == '-') return 2;
        else if(token == '(') return 1;
        else if(token == ')') return -1;
        else return 0;
    }
}
