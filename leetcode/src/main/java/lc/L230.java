package lc;

import java.util.Stack;

/**
 * Created by zz on 2021/10/31.
 */
public class L230 {
    private final Stack<Integer> stack = new Stack<>();

    public int kthSmallest(TreeNode root, int k) {
        pushStack(root, k);
        return stack.peek();
    }

    private void pushStack(TreeNode root, int k) {
        if (root == null) return;
        pushStack(root.left, k);
        if (stack.size() == k) {
            return;
        } else {
            stack.push(root.val);
        }
        pushStack(root.right, k);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
