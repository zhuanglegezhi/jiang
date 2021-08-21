package lc;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by zz on 2021/8/21.
 */
public class L20 {
    public boolean isValid(String s) {
        if (s == null) return true;
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {

            Character top = stack.empty() ? null : stack.peek();
            Character pair = map.get(c);
            if (pair != null && pair.equals(top)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.empty();
    }
}
