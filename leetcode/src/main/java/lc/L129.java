package lc;

/**
 * Created by zz on 2021/8/5.
 */
public class L129 {
    private int sum = 0;

    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    private void dfs(TreeNode root, int tmp) {
        if (root == null) {
            return;
        }
        tmp = tmp * 10 + root.val;
        if (root.left == null && root.right == null) {
            sum += tmp;
            return;
        } else {
            dfs(root.left, tmp);
            dfs(root.right, tmp);
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
