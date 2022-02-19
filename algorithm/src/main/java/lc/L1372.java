package lc;

/**
 * Created by zz on 2021/7/27.
 */
public class L1372 {

    private int max = 0;

    public int longestZigZag(TreeNode root) {
        if (root == null) return max;
        dfs(root, 0, true);
        dfs(root, 0, false);
        return max;
    }

    private void dfs(TreeNode node, int count, boolean left) {
        max = Math.max(count, max);
        if (node == null) {
            return;
        }
        if (left) {
            // 往左走的情况
            if (node.left != null) {
                dfs(node.left, count + 1, false);
            }
            // 往右走的情况，重新开始
            if (node.right != null) {
                dfs(node.right, 1, true);
            }
        }else {
            if (node.right != null) {
                dfs(node.right, count + 1, true);
            }
            if (node.left != null) {
                dfs(node.left, 1, false);
            }
        }
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
