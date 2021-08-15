package lc;

/**
 * Created by zz on 2021/8/15.
 */
public class L701 {

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

    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode dummy = new TreeNode(Integer.MAX_VALUE);
        dummy.left = root;
        dfs(dummy, root, val);
        return dummy.left;
    }

    private void dfs(TreeNode parent, TreeNode root, int val) {
        if (root == null) {
            if (parent == null) {
                return;
            } else {
                if (parent.val > val) {
                    parent.left = new TreeNode(val);
                }else {
                    parent.right = new TreeNode(val);
                }
                return;
            }
        }

        if (root.val > val) {
            dfs(root, root.left, val);
        } else {
            dfs(root, root.right, val);
        }
    }
}
