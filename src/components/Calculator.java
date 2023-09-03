package components;

import java.util.Stack;

class Calculator {
    private static String expToRPN(String exp) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        int priority;
        for (int i = 0; i < exp.length(); i++) {
            priority = getP(exp.charAt(i));
            if (priority == -2) {
                return "WRONG";
            }
            if (priority == 0) {
                sb.append(exp.charAt(i));
            }
            if (priority == 1) {
                stack.push(exp.charAt(i));
            }
            if (priority > 1) {
                sb.append(' ');
                while (!stack.isEmpty()) {
                    if (getP(stack.peek()) >= priority)
                        sb.append(stack.pop());
                    else break;
                }
                stack.push(exp.charAt(i));
            }
            if (priority == -1) {
                sb.append(' ');
                while (getP(stack.peek()) != 1)
                    sb.append(stack.pop());
                stack.pop();
            }
        }
        while (!stack.isEmpty())
            sb.append(stack.pop());
        return sb.toString();
    }

    private static int RPNtoAnswer(String rpn) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ')
                continue;
            StringBuilder sb = new StringBuilder();
            if (rpn.charAt(i) != ' ' && getP(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) != ' ' && getP(rpn.charAt(i)) == 0) {
                    sb.append(rpn.charAt(i++));
                    if (i == rpn.length())
                        break;
                }
                stack.push(Integer.parseInt(sb.toString()));
            }
            if (getP(rpn.charAt(i)) > 1) {
                int a = stack.pop(), b = stack.pop();
                if (rpn.charAt(i) == '+')
                    stack.push(b + a);
                if (rpn.charAt(i) == '-')
                    stack.push(b - a);
                if (rpn.charAt(i) == '*')
                    stack.push(b * a);
                if (rpn.charAt(i) == '/')
                    stack.push(b / a);
            }
        }
        return stack.pop();
    }
    private static int getP(char token) {
        if (token == '*' || token == '/')
            return 3;
        if (token == '+' || token == '-') {
            return 2;
        }
        if (token == '(') {
            return 1;
        }
        if (token == ')') {
            return -1;
        }
        if (token >= '0' && token <= '9') {
            return 0;
        }
        return -2;
    }

    private static String decide(String exp) {
        String prepared = preparingStr(exp);
        return expToRPN(prepared);
    }
    private static String preparingStr(String exp) {
        StringBuilder preparedStr = new StringBuilder();
        for (int token = 0; token < exp.length(); token++) {
            if (exp.charAt(token) == '-') {
                if (token == 0 || exp.charAt(token - 1) == '(') {
                    preparedStr.append('0');
                }
            }
            preparedStr.append(exp.charAt(token));
        }
        return preparedStr.toString();
    }

    public static int getResult(String text) throws IllegalArgumentException {
        String rpn = decide(text);
        if (rpn.equals("WRONG")) {
            throw new IllegalArgumentException("error");
        }
        return RPNtoAnswer(rpn);
    }
}
