package lc;

/**
 * Created by zz on 2021/9/2.
 */
public class L437 {
    private int count = 0;

    public int pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum, false);
        dfs(root, targetSum, true);
        return count;
    }

    private void dfs(TreeNode root, int targetSum, boolean select) {
        if (root == null) {
            return;
        }
        if (select) {
            targetSum = targetSum - root.val;
            if (targetSum == 0) {
                count++;
            }
            dfs(root.left, targetSum, true);
            dfs(root.right, targetSum, true);
        } else {
            dfs(root.left, targetSum, false);
            dfs(root.right, targetSum, false);
            dfs(root.left, targetSum, true);
            dfs(root.right, targetSum, true);
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
