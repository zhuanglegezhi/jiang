package lc;

/**
 * Created by zz on 2021/8/5.
 */
public class L814 {
    public TreeNode pruneTree(TreeNode root) {
        TreeNode dummy = new TreeNode();
        dummy.left = root;
        doPrune(dummy);
        return dummy.left;
    }

    private void doPrune(TreeNode root) {
        if (root == null) return;
        if (need(root.left)) root.left = null;
        if (need(root.right)) root.right = null;
        doPrune(root.left);
        doPrune(root.right);
    }

    private boolean need(TreeNode root) {
        if (root == null) return true;
        if (root.val == 1) return false;
        return need(root.left) && need(root.right);
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
