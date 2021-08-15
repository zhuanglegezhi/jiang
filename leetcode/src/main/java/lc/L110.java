package lc;

/**
 * Created by zz on 2021/8/15.
 */
public class L110 {

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

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(deep(root.left) - deep(root.right)) <= 1 &&
                isBalanced(root.left) && isBalanced(root.right);
    }

    private int deep(TreeNode root) {
        if (root == null) return 0;
        return Math.max(deep(root.right), deep(root.left)) + 1;
    }
}
