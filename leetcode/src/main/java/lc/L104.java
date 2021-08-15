package lc;

/**
 * Created by zz on 2021/8/15.
 */
public class L104 {
    private int max = 0;

    public int maxDepth(TreeNode root) {
        dfs(root, 0);
        return max;
    }

    private int dfs(TreeNode root, int depth) {
        if (root == null) {
            max = Math.max(max, depth);
            return depth;
        }
        int l = dfs(root.left, depth + 1);
        int r = dfs(root.right, depth + 1);
        return Math.max(l, r);
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
