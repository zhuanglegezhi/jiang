package lc;

/**
 * Created by zz on 2021/8/7.
 */
public class L1026 {

    private int max = 0;

    public int maxAncestorDiff(TreeNode root) {
        dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return max;
    }

    public void dfs(TreeNode root, int lower, int upper) {
        if (root == null) return;
        max = Math.max(max,
                Math.max(lower == Integer.MIN_VALUE ? 0 : Math.abs(lower - root.val),
                        upper == Integer.MAX_VALUE ? 0 : Math.abs(upper - root.val))
        );

        lower = lower == Integer.MIN_VALUE ? root.val : Math.min(lower, root.val);
        upper = upper == Integer.MAX_VALUE ? root.val : Math.max(upper, root.val);


        dfs(root.left, lower, upper);
        dfs(root.right, lower, upper);
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
