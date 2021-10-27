package lc;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by zz on 2021/7/31.
 */
public class L116 {

    public Node connect(Node root) {
        if (root == null) return root;

        Deque<Node> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                Node node = deque.poll();
                if (i != size - 1) {
                    node.next = deque.peek();
                }
                if (node.left != null) {
                    deque.offer(node.left);
                }
                if (node.right != null) {
                    deque.offer(node.right);
                }
            }
        }
        return root;
    }

    public Node connectV2(Node root) {
        if (root == null) return null;
        connect(root.left, root.right);
        return root;
    }

    private void connect(Node left, Node right) {
        if (left == null || right == null) return;
        left.next = right;
        connect(left.left, left.right);
        connect(right.left, right.right);
        connect(left.right, right.left);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
