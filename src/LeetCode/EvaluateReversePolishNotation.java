package LeetCode;

import java.util.Stack;

/**
 * @Description :
 * @Author : chenzhitao
 * @Date : Created in 19:45 2018/12/25
 */
public class EvaluateReversePolishNotation {

    public static void main(String[] args) {
        EvaluateReversePolishNotation demo = new EvaluateReversePolishNotation();
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println(demo.evalRPN(tokens));
    }


    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            throw new NullPointerException();
        }

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            try {
                int val = Integer.parseInt(tokens[i]);
                stack.push(val);
            } catch (Exception e) {
                String operation = tokens[i];
                if (stack.size() >= 2) {
                    // 后进先出
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(calculate(a, b, operation));
                } else {
                    throw new IllegalArgumentException("param.tokens include val is error!");
                }
            }
        }
        return stack.pop();


    }

    private int calculate(int a, int b, String operation) {
        switch (operation) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new IllegalArgumentException("param.b is zero, not support division");
                }
                return a / b;
            default:
                return 0;
        }
    }
}
