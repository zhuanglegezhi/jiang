package lc;

import java.util.Stack;

/**
 * Created by zz on 2021/8/29.
 */
public class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;


    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        stack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
