package 剑指;

import java.util.Stack;

/**
 * Created by zz on 2022/2/19.
 */
public class O09 {

    class CQueue {

        private final Stack<Integer> stack1;
        private final Stack<Integer> stack2;

        public CQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            if (stack1.isEmpty() && stack2.isEmpty()) {
                return -1;
            }

            if (!stack2.isEmpty()) {
                return stack2.pop();
            }


            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }

            return stack2.pop();
        }
    }
}
