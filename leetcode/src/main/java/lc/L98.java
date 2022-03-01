package lc;

/**
 * Created by zz on 2021/8/1.
 */
public class L98 {

    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 通过上下界保证孙子节点和爷爷节点也符合大小关系，是一个交集
     */
    private boolean dfs(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val >= max || node.val <= min) return false;
        return dfs(node.left, min, node.val) && dfs(node.right, node.val, max);
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
