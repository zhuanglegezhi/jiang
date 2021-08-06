package lc;

/**
 * Created by zz on 2021/8/6.
 */
public class L1325 {

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


    public TreeNode removeLeafNodes(TreeNode root, int target) {
        TreeNode dummy = new TreeNode();
        dummy.left = root;
        dfs(dummy, dummy, target);
        return dummy.left;
    }

    private void dfs(TreeNode dummy, TreeNode root, int target) {
        if (root == null) return;
        TreeNode left = root.left;
        if (left != null && left.left == null && left.right == null && left.val == target) {
            root.left = null;
            dfs(dummy, dummy, target);
            return;
        }

        TreeNode right = root.right;
        if (right != null && right.left == null && right.right == null && right.val == target) {
            root.right = null;
            dfs(dummy, dummy, target);
            return;
        }

        dfs(dummy, root.left, target);
        dfs(dummy, root.right, target);
    }

}
