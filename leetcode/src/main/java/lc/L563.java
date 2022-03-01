package lc;

/**
 * Created by zz on 2021/8/4.
 */
public class L563 {

    private int count = 0;

    public int findTilt(TreeNode root) {
        dfs(root);
        return count;
    }

    /**
     * 计算root节点下的所有点的和
     */
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        count += Math.abs(left - right);            // 迭代到该点时，计算改点的坡度并更新count
        return left + right + root.val;
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
